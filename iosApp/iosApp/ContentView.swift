import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject private(set) var viewModel: ViewModel
    
    init(viewModel: ViewModel) {
        self.viewModel = viewModel
        self.viewModel.fetchData()
    }
    
	var body: some View {
        if (viewModel.uiState.isLoading) {
            ProgressView()
        }
        
        if (!viewModel.uiState.goods.isEmpty) {
            List(viewModel.uiState.goods, id: \.id) { item in
                Text(item.title).font(.title)
                Text(item.description).font(.subheadline).padding([.trailing], 8)
                AsyncImage(url: URL(string: item.images[0]))
            }.ignoresSafeArea()
        }
        
        if (viewModel.uiState.isError) {
            Text("Произошла ошибка")
            Button("Попробовать снова", action: viewModel.fetchData).buttonStyle(.bordered)
        }
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
        private let repository: StoreRepository
        
        @Published var uiState: GoodUiState = GoodUiState(isLoading: true)
        
        //@Published var uiState: [GoodUi] = []
        //@Published
        //@Published var isError: Bool = false
        
        init(repository: StoreRepository) {
            self.repository = repository
        }
        
        func fetchData() {
            repository.fetchGoods { result, e in
                switch result {
                case let success as LoadResult.Success:
                    let goods = success.data.map { item in
                        item.toGoodUi()
                    }
                    DispatchQueue.main.async {
                        self.uiState = GoodUiState(goods: goods)
                    }
                case let error as LoadResult.Error:
                    DispatchQueue.main.async {
                        self.uiState = GoodUiState(isError: true)
                    }
                default:
                    print("error")
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

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
