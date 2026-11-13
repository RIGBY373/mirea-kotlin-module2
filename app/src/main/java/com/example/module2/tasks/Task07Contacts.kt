package com.example.module2.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.module2.R

data class Contact(
    val name: String,
    val patronymic: String,
    val surname: String,
    val phone: String?,
    val address: String,
    val isFavorite: Boolean = false,
)

val sampleContacts = listOf(
    Contact(
        name = "Евгений",
        patronymic = "Андреевич",
        surname = "Лукашин",
        phone = "+7 495 495 95 95",
        address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
        isFavorite = true,
    ),
    Contact(
        name = "Василий",
        patronymic = "Егорович",
        surname = "Кузякин",
        phone = null,
        address = "Ивановская область, дер. Крутово, д. 4",
    ),
    Contact(
        name = "Людмила",
        patronymic = "Прокофьевна",
        surname = "Калугина",
        phone = "+7 495 788 78 78",
        address = "Москва, Большая Никитская, д. 43, кв. 290",
    ),
)

/** Задание 7.1. Сведения о контакте — строки друг под другом (Column). */
@Composable
fun ContactDetails(
    contact: Contact,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    textAlign: TextAlign = TextAlign.Start,
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        Text(text = "Имя: ${contact.name}", textAlign = textAlign)
        Text(text = "Отчество: ${contact.patronymic}", textAlign = textAlign)
        Text(text = "Фамилия: ${contact.surname}", textAlign = textAlign)
        Text(text = "Мобильный телефон: ${contact.phone ?: "---"}", textAlign = textAlign)
        Text(text = "Адрес: ${contact.address}", textAlign = textAlign)
    }
}

/** Карточка контакта: серый фон, текст по центру. */
@Composable
fun ContactCard(contact: Contact, modifier: Modifier = Modifier) {
    ContactDetails(
        contact = contact,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        textAlign = TextAlign.Center,
    )
}

/** Задание 7.2. Список контактов в Column с промежутками между карточками. */
@Composable
fun ContactList(contacts: List<Contact>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        contacts.forEach { contact -> ContactCard(contact) }
    }
}

/** Задание 7.3. Контакт и значок «избранное» в одной строке (Row). */
@Composable
fun FavoriteContactCard(contact: Contact, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ContactDetails(
            contact = contact,
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            textAlign = TextAlign.Center,
        )
        if (contact.isFavorite) {
            Image(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = stringResource(R.string.favorite_description),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(32.dp),
            )
        }
    }
}

/** Экран приложения со всеми тремя вариантами. */
@Composable
fun ContactsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Column: один контакт", style = MaterialTheme.typography.titleMedium)
        ContactDetails(contact = sampleContacts.first())
        HorizontalDivider()
        Text("Column: список контактов", style = MaterialTheme.typography.titleMedium)
        ContactList(contacts = sampleContacts)
        HorizontalDivider()
        Text("Row: избранный контакт", style = MaterialTheme.typography.titleMedium)
        FavoriteContactCard(contact = sampleContacts.first())
    }
}

@Preview(name = "ContactColumnPreview", showBackground = true)
@Composable
fun ContactDetailsPreview() {
    ContactDetails(contact = sampleContacts.first())
}

@Preview(name = "ListPreview", showBackground = true)
@Composable
fun ContactListPreview() {
    ContactList(contacts = sampleContacts, modifier = Modifier.padding(8.dp))
}

@Preview(name = "ContactRowPreview", showBackground = true)
@Composable
fun FavoriteContactCardPreview() {
    FavoriteContactCard(contact = sampleContacts.first(), modifier = Modifier.padding(8.dp))
}
