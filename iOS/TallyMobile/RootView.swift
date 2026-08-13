import SwiftUI

struct RootView: View {
    @State private var tab = 0
    var body: some View {
        TabView(selection:$tab) {
            NavigationStack { HomeView { tab = 1 } }.tabItem { Label("Home",systemImage:"house") }.tag(0)
            NavigationStack { BuilderView() }.tabItem { Label("Create",systemImage:"plus.square") }.tag(1)
            NavigationStack { PricingView { tab = 1 } }.tabItem { Label("Pricing",systemImage:"star.circle") }.tag(2)
        }.tint(.tallyBlue)
    }
}
