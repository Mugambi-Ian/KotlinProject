import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        KoinKt.doInitKoin(appDeclaration: { _ in }, platformModules: [KoinIosModuleKt.iosModule])
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}