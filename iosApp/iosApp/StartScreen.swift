//
//  StartScreen.swift
//  iosApp
//
//  Created by ilya on 15.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct StartScreen: View {
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                NavigationLink(destination: NetworkScreen()) {
                    Text("Сеть")
                }
                
                NavigationLink(destination: DatabaseScreen()) {
                    Text("База данных")
                }
            }
        }
    }
}
