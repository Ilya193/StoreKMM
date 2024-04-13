import SwiftUI
import shared

@main
struct iOSApp: App {    
    
    init() {
        StoreHelperKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
            ContentView(viewModel: ContentView.ViewModel(repository: StoreHelper().storeRepository))
		}
	}
}
