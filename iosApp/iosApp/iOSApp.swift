import SwiftUI
import shared
    
@main
struct iOSApp: App {    
    
    init() {
        StoreHelperKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
            StartScreen()
		}
	}
}
