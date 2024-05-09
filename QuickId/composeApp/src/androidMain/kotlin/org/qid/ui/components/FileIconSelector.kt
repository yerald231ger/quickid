package org.qid.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import core.constants.IdentityFileType
import org.qid.R

@Composable
fun fileIconSelector(identityFileType: IdentityFileType): Painter {
    return when (identityFileType) {
        IdentityFileType.ID -> painterResource(id = R.drawable.ic_id)
        IdentityFileType.CONTRACT -> painterResource(id = R.drawable.ic_contract)
        IdentityFileType.SOCIAL_SECURITY -> painterResource(id = R.drawable.ic_social_security)
        IdentityFileType.PASSPORT -> painterResource(id = R.drawable.ic_passport)
        IdentityFileType.CAR_INSURANCE -> painterResource(id = R.drawable.ic_car_insurance)
        IdentityFileType.DRIVER_LICENSE -> painterResource(id = R.drawable.ic_driver_license)
        IdentityFileType.OTHER -> painterResource(id = R.drawable.ic_file)
    }
}