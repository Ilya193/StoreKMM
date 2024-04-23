//
//  DatabaseScreen.swift
//  iosApp
//
//  Created by ilya on 15.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DatabaseScreen: View {
    @State private var showAlert = false
    @State private var note: String = ""
    
    @ObservedObject private(set) var viewModel: ViewModel
    
    init() {
        self.viewModel = ViewModel(repository: NotesHelper().injectNotesRepository())
        self.viewModel.fetchData()
    }
    
    var body: some View {
        NavigationView {
            List(viewModel.uiState.notes, id: \.id) { item in
                Text(item.title).frame(maxWidth: .infinity, alignment: .leading).contentShape(Rectangle()).onLongPressGesture(minimumDuration: 0.1) {
                    viewModel.deleteNoteById(id: item.id)
                }
            }
        }.toolbar {
            ToolbarItem {
                Image(systemName: "plus").onTapGesture {
                    showAlert.toggle()
                }.alert(Text("Название заметки"), isPresented: $showAlert) {
                    TextField("KMM", text: $note)
                    Button("Отменить", role: .cancel) {
                        showAlert.toggle()
                    }
                    Button("Добавить") {
                        viewModel.insertNote(title: note)
                        note = ""
                        showAlert.toggle()
                    }
                }
            }
        }.ignoresSafeArea()
    }
}

extension DatabaseScreen {
    class ViewModel: ObservableObject {
        private let repository: NotesRepository
        
        @Published var uiState: NotesUiState = NotesUiState()
        
        init(repository: NotesRepository) {
            self.repository = repository
        }
        
        func fetchData() {
            let observer = Observer<[NoteDomain]>(callback: { notes in
                if let notesUi = notes?.map({ note in
                    note.toNoteUi()
                }) {
                    DispatchQueue.main.async {
                        self.uiState = NotesUiState(notes: notesUi)
                    }
                }
            })
            
            do {
                try repository.fetchNotes().collect(collector: observer, completionHandler: { _ in })
            } catch {}
        }
        
        func insertNote(title: String) {
            repository.insertNote(title: title) { _ in }
        }
        
        func deleteNoteById(id: Int64) {
            repository.deleteById(id: id) { _ in }
        }
    }
}

class Observer<T> : Kotlinx_coroutines_coreFlowCollector {
    let callback: (T?) -> Void
    
    init(callback: @escaping (T?) -> Void) {
        self.callback = callback
    }
    
    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        callback(value as? T)
        completionHandler(nil)
    }
}

extension NoteDomain {
    func toNoteUi() -> NoteUi {
        return NoteUi(id: id, title: title)
    }
}

struct NoteUi: Identifiable {
    let id: Int64
    let title: String
}

struct NotesUiState {
    var notes: [NoteUi] = []
    var showDialog: Bool = false
}
