package org.example.project.modules.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import compose.icons.FeatherIcons
import compose.icons.feathericons.Mail
import org.example.project.modules.dashboard.data.model.User


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserView(
    user: User
) {
    Box(
        modifier = Modifier.padding(10.dp, 10.dp)
    ) {
        user.profilePicture?.let {
            AsyncImage(
                modifier = Modifier.size(150.dp)
                    .clip(RoundedCornerShape(100)),
                contentScale = ContentScale.Crop,
                model = user.profilePicture,
                contentDescription = null,
            )
        }
    }
    Column(modifier = Modifier.padding(10.dp, 40.dp, 0.dp, 0.dp).fillMaxWidth()) {
        Text(text = user.name, fontWeight = FontWeight.Bold, fontSize = 28.sp)
        Row(modifier = Modifier.padding(2.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = FeatherIcons.Mail,
                contentDescription = "Email Icon",
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = user.language,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                modifier = Modifier.padding(10.dp, 0.dp)
            )
        }
    }

}