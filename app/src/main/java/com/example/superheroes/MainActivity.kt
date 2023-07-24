package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    SuperheroApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { HeroTopAppBar() },
        modifier = modifier,
    ) {
        LazyColumn(contentPadding = it) {
            items(HeroesRepository.heroes) { hero ->
                HeroItem(
                    hero = hero,
                    modifier = Modifier.padding(
                        dimensionResource(id = R.dimen.padding_medium),
                        dimensionResource(id = R.dimen.padding_small),
                    ),
                )
            }
        }
    }
}

@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier,){
    Box(
        contentAlignment = Alignment.Center,
        modifier= modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ){
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
        )
    }
}

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            HeroInformation(
                hero.nameRes, hero.descriptionRes, modifier = Modifier.weight(1f)
            )
            HeroIcon(hero.imageRes)
        }
    }
}


@Composable
fun HeroInformation(
    @StringRes nameRes: Int,
    @StringRes descriptionRes: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = nameRes),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
            style = MaterialTheme.typography.displayMedium,
        )
        Text(
            text = stringResource(id = descriptionRes),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun HeroIcon(@DrawableRes imageRes: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.image_size))
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = imageRes),
        contentDescription = null,
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuperheroAppPreview() {
    SuperheroesTheme(darkTheme = false) {
        SuperheroApp()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuperheroAppPreviewDark() {
    SuperheroesTheme(darkTheme = true) {
        SuperheroApp()
    }
}