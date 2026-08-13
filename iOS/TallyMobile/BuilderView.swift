import SwiftUI

struct BuilderView: View {
    @EnvironmentObject private var store: FormBuilderStore
    @State private var showPicker = false
    var body: some View {
        ScrollView { VStack(spacing:14) {
            TextField("Untitled form",text:$store.draft.title,axis:.vertical).font(.system(size:31,weight:.bold)).padding(.bottom,5)
            if let toast=store.toast { Text(toast).font(.caption).foregroundStyle(.green).frame(maxWidth:.infinity,alignment:.leading).transition(.opacity) }
            ForEach($store.draft.blocks) { $block in BlockEditor(block:$block,onDelete:{store.delete(block)},onUp:{store.move(block,by:-1)},onDown:{store.move(block,by:1)}) }
            Button { showPicker=true } label:{Label("Add block",systemImage:"plus.circle.fill").frame(maxWidth:.infinity).padding(8)}.buttonStyle(.bordered).padding(.top,8)
        }.padding(20).padding(.bottom,40) }
        .background(Color.tallyCanvas).navigationTitle("Create form").navigationBarTitleDisplayMode(.inline)
        .toolbar { ToolbarItemGroup(placement:.topBarTrailing){Button("Save"){store.save()};Button("Publish"){store.save("Form published (demo)")}.buttonStyle(.borderedProminent)} }
        .sheet(isPresented:$showPicker){BlockPicker{store.add($0);showPicker=false}.presentationDetents([.medium])}
    }
}

private struct BlockEditor:View{
    @Binding var block:FormBlock;let onDelete,onUp,onDown:()->Void
    var body:some View{VStack(alignment:.leading,spacing:12){HStack{Label(block.type.title,systemImage:block.type.symbol).font(.caption).foregroundStyle(.secondary);Spacer();Button(action:onUp){Image(systemName:"chevron.up")};Button(action:onDown){Image(systemName:"chevron.down")};Button(role:.destructive,action:onDelete){Image(systemName:"trash")}};TextField("Question",text:$block.prompt,axis:block.type == .long || block.type == .text ? .vertical:.horizontal).textFieldStyle(.roundedBorder);if block.type == .choice{HStack{Label("Option 1",systemImage:"circle");Label("Option 2",systemImage:"circle")}.font(.subheadline).foregroundStyle(.secondary)};if block.type == .file{Button("Choose file"){}.buttonStyle(.bordered)}}.padding(16).background(.white,in:RoundedRectangle(cornerRadius:15)).overlay{RoundedRectangle(cornerRadius:15).stroke(.black.opacity(.07))}}
}

private struct BlockPicker:View{let choose:(BlockType)->Void;var body:some View{NavigationStack{LazyVGrid(columns:[GridItem(.flexible()),GridItem(.flexible()),GridItem(.flexible())],spacing:15){ForEach(BlockType.allCases){type in Button{choose(type)}label:{VStack(spacing:10){Image(systemName:type.symbol).font(.title2);Text(type.title).font(.caption).multilineTextAlignment(.center)}.frame(maxWidth:.infinity,minHeight:90).background(Color.tallyBlue.opacity(.08),in:RoundedRectangle(cornerRadius:14))}}}.padding().navigationTitle("Add a block").navigationBarTitleDisplayMode(.inline)}}}
