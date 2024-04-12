import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject private(set) var viewModel: ViewModel
    
    init(viewModel: ViewModel) {
        self.viewModel = viewModel
        self.viewModel.fetchData()
    }
    
	var body: some View {
        List(viewModel.uiState, id: \.id) { item in
            Text(item.title)
            AsyncImage(url: URL(string: item.images[0]))
        }.ignoresSafeArea()
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
        private let repository: StoreRepository
        
        @Published var uiState: [GoodUi] = []
        
        init(repository: StoreRepository) {
            self.repository = repository
        }
        
        func fetchData() {
            repository.fetchGoods { result, error in
                switch result {
                case let success as LoadResult.Success:
                    let goods = success.data.map { item in
                        item.toGoodUi()
                    }
                    DispatchQueue.main.async {
                        self.uiState = goods
                    }
                case let e as LoadResult.Error:
                    print("\(e)")
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

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
