package org.qid

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.qid.core.models.IdentityFile

class IndexViewModel {

    var topIdentityFiles by mutableStateOf<List<IdentityFile>>(emptyList())
        private set

}