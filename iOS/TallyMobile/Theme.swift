import SwiftUI

extension Color {
    static let tallyBlue = Color(red: 0, green: 0.44, blue: 0.84)
    static let tallyPink = Color(red: 0.97, green: 0.11, blue: 0.90)
    static let tallyInk = Color(red: 0.22, green: 0.21, blue: 0.18)
    static let tallyCanvas = Color(red: 1, green: 0.99, blue: 0.98)
}

struct TallyLogo: View {
    var body: some View { HStack(spacing: 8) { Text("T").font(.headline.bold()).foregroundStyle(.white).frame(width: 32,height: 32).background(Color.tallyInk,in:RoundedRectangle(cornerRadius:8)); Text("tally").font(.title3.bold()) } }
}

struct PrimaryButtonStyle: ButtonStyle {
    var dark = false
    func makeBody(configuration: Configuration) -> some View { configuration.label.fontWeight(.semibold).foregroundStyle(.white).frame(maxWidth:.infinity).padding(.vertical,14).background(dark ? Color.tallyInk : Color.tallyBlue,in:RoundedRectangle(cornerRadius:10)).opacity(configuration.isPressed ? 0.75 : 1) }
}
