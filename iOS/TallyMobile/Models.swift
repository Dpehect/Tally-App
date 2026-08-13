import Foundation

enum BlockType: String, CaseIterable, Codable, Identifiable {
    case text, short, long, choice, email, file
    var id: String { rawValue }
    var title: String { switch self { case .text: "Text"; case .short: "Short answer"; case .long: "Long answer"; case .choice: "Multiple choice"; case .email: "Email"; case .file: "File upload" } }
    var symbol: String { switch self { case .text: "textformat"; case .short: "character.cursor.ibeam"; case .long: "text.alignleft"; case .choice: "checklist"; case .email: "at"; case .file: "arrow.up.doc" } }
    var prompt: String { switch self { case .text: "Add some text here…"; case .short: "New question"; case .long: "Tell us more"; case .choice: "Choose one option"; case .email: "Email address"; case .file: "Upload a file" } }
}

struct FormBlock: Identifiable, Codable, Equatable { var id = UUID(); var type: BlockType; var prompt: String }
struct FormDraft: Codable { var title = "Untitled form"; var blocks = [FormBlock(type: .short, prompt: "What is your name?"), FormBlock(type: .email, prompt: "Email address")] }

@MainActor final class FormBuilderStore: ObservableObject {
    @Published var draft: FormDraft
    @Published var toast: String?
    private let key = "tally.form.draft"
    init() { draft = UserDefaults.standard.data(forKey: key).flatMap { try? JSONDecoder().decode(FormDraft.self, from: $0) } ?? FormDraft() }
    func add(_ type: BlockType) { draft.blocks.append(FormBlock(type: type, prompt: type.prompt)) }
    func delete(_ block: FormBlock) { draft.blocks.removeAll { $0.id == block.id } }
    func move(_ block: FormBlock, by delta: Int) { guard let from = draft.blocks.firstIndex(of: block) else { return }; let to = min(max(0, from + delta), draft.blocks.count - 1); guard to != from else { return }; draft.blocks.move(fromOffsets: IndexSet(integer: from), toOffset: to > from ? to + 1 : to) }
    func save(_ message: String = "Draft saved on this device") { if let data = try? JSONEncoder().encode(draft) { UserDefaults.standard.set(data, forKey: key); toast = message } }
}
