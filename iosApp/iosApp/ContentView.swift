import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject private(set) var viewModel: ViewModel
    
	var body: some View {
        Text(viewModel.text)
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var text = "Секундочку..."
        
        init() {
            Network().fetchData { result, error in
                DispatchQueue.main.async {
                    self.text = result ?? "Ошибка"
                }
            }
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
