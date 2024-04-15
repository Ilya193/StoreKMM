import SwiftUI
import shared

struct NetworkScreen: View {
    
    @ObservedObject private(set) var viewModel: ViewModel
    
    init() {
        self.viewModel = ViewModel(repository: StoreHelper().injectStoreRepository())
        self.viewModel.fetchData()
    }
    
	var body: some View {
        if (viewModel.uiState.isLoading) {
            ProgressView()
        }
        
        if (!viewModel.uiState.goods.isEmpty) {
            NavigationView {
                List(viewModel.uiState.goods, id: \.id) { item in
                    GoodView(good: item)
                }
            }.ignoresSafeArea()
        }
        
        if (viewModel.uiState.isError) {
            Text("Произошла ошибка")
            Button("Попробовать снова", action: viewModel.fetchData).buttonStyle(.bordered)
        }
	}
}

struct GoodView: View {
    private let good: GoodUi
    
    init(good: GoodUi) {
        self.good = good
    }
    
    var body: some View {
        Text(good.title).font(.title)
        Text(good.description).font(.subheadline).padding([.trailing], 8)
        AsyncImage(url: URL(string: good.images[0]))
    }
}

extension NetworkScreen {
    class ViewModel: ObservableObject {
        private let repository: StoreRepository
        
        @Published var uiState: GoodUiState = GoodUiState(isLoading: true)
        
        init(repository: StoreRepository) {
            self.repository = repository
        }
        
        func fetchData() {
            repository.fetchGoods { result, e in
                switch result {
                case let success as GoodsLoadResult.Success:
                    let goods = success.data.map { item in
                        item.toGoodUi()
                    }
                    DispatchQueue.main.async {
                        self.uiState = GoodUiState(goods: goods)
                    }
                default:
                    DispatchQueue.main.async {
                        self.uiState = GoodUiState(isError: true)
                    }
                }
            }
        }
    }
}

extension GoodDomain {
    func toGoodUi() -> GoodUi {
        return GoodUi(id: id, title: title, images: images, description: description_)
    }
}

struct GoodUi: Identifiable {
    let id: Int32
    let title: String
    let images: [String]
    let description: String
}

struct GoodUiState {
    var goods: [GoodUi] = []
    var isLoading: Bool = false
    var isError: Bool = false
}
