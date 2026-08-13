import SwiftUI

struct HomeView: View {
    let onCreate: () -> Void
    var body: some View {
        ScrollView { VStack(spacing:0) {
            HStack { TallyLogo(); Spacer(); Button("Create form",action:onCreate).fontWeight(.semibold) }.padding(.horizontal,24).padding(.top,18)
            VStack(spacing:18) { Text("The simplest way to\ncreate forms").font(.system(size:46,weight:.bold,design:.rounded)).multilineTextAlignment(.center).tracking(-1.8); Text("Say goodbye to boring forms. Meet a free, intuitive form builder that works like a document.").font(.title3).foregroundStyle(.secondary).multilineTextAlignment(.center).lineSpacing(5); Button("Create a free form  →",action:onCreate).buttonStyle(PrimaryButtonStyle()).frame(maxWidth:260); Text("No signup required").font(.caption).foregroundStyle(.secondary) }.padding(.horizontal,25).padding(.top,62)
            ProductMock().padding(24).padding(.top,22)
            SectionHeader(title:"A form builder like no other",subtitle:"No code needed — just type your questions like you would in a doc.")
            FeatureCard(icon:"infinity",title:"Unlimited forms",body:"Create as many forms and collect as many fair-use submissions as you need.",color:Color.pink.opacity(0.09))
            FeatureCard(icon:"command",title:"Just start typing",body:"Build with familiar, Notion-style content blocks.",color:Color.blue.opacity(0.08))
            FeatureCard(icon:"lock.shield",title:"Privacy-friendly",body:"Designed with European privacy and GDPR principles in mind.",color:Color.green.opacity(0.09))
            SectionHeader(title:"Simple but powerful",subtitle:"Contact details, files, choices and more — all in one calm interface.")
            VStack(spacing:20) { FeatureRow(title:"Contact info",body:"Names, emails and links"); FeatureRow(title:"Multiple choice",body:"Ratings, scales and options"); FeatureRow(title:"File uploads",body:"Documents, photos and media"); FeatureRow(title:"Smart forms",body:"Conditional, tailored journeys") }.padding(.horizontal,28)
            VStack(alignment:.leading,spacing:12){Text("Ready to create your first form?").font(.title.bold());Text("Unlimited drafts. No credit card required.").foregroundStyle(.gray);Button("Start building",action:onCreate).buttonStyle(PrimaryButtonStyle())}.foregroundStyle(.white).padding(28).background(Color.tallyInk,in:RoundedRectangle(cornerRadius:22)).padding(24).padding(.top,30)
        }}.background(Color.tallyCanvas)
    }
}

private struct ProductMock: View { var body: some View { VStack(spacing:10){HStack{ForEach(0..<3){_ in Circle().fill(.gray.opacity(.25)).frame(width:8,height:8)};Spacer()};Text("✨").font(.system(size:42)).padding(.top,20);Text("Just start typing").font(.title3.bold());Text("Build forms like a document").foregroundStyle(.secondary).padding(.bottom,20)}.padding(22).frame(maxWidth:.infinity).background(.white,in:RoundedRectangle(cornerRadius:18)).shadow(color:.black.opacity(.1),radius:16,y:8)} }
private struct SectionHeader: View { let title,subtitle:String; var body:some View{VStack(alignment:.leading,spacing:10){Text(title).font(.system(size:32,weight:.bold));Text(subtitle).foregroundStyle(.secondary).lineSpacing(4)}.frame(maxWidth:.infinity,alignment:.leading).padding(.horizontal,25).padding(.top,52).padding(.bottom,18)} }
private struct FeatureCard: View { let icon,title,body:String;let color:Color;var body:some View{VStack(alignment:.leading,spacing:10){Image(systemName:icon).font(.title).foregroundStyle(Color.tallyPink);Text(title).font(.title3.bold()).padding(.top,15);Text(body).foregroundStyle(.secondary).lineSpacing(3)}.frame(maxWidth:.infinity,alignment:.leading).padding(24).background(color,in:RoundedRectangle(cornerRadius:19)).padding(.horizontal,24).padding(.vertical,6)} }
private struct FeatureRow:View{let title,body:String;var body:some View{HStack(alignment:.top,spacing:15){Circle().fill(Color.tallyPink).frame(width:8,height:8).padding(.top,6);VStack(alignment:.leading,spacing:3){Text(title).fontWeight(.bold);Text(body).font(.subheadline).foregroundStyle(.secondary)};Spacer()}}}
