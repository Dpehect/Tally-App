import SwiftUI

@main
struct TallyMobileApp: App {
    @StateObject private var builder = FormBuilderStore()
    var body: some Scene { WindowGroup { RootView().environmentObject(builder) } }
}
