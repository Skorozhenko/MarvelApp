package com.example.marvelapp.screens.components

import com.example.marvelapp.R

data class Hero(
    val id: Int,
    val name: Int,
    var info: Int,
    val imageUrl : String
)

fun List<Hero>.getHeroById(heroId: Int): Hero? {
    return this.find { it.id == heroId }
}

fun getHeroById(heroId: Int) = heroes.getHeroById(heroId)


val heroes = listOf(
    Hero(
        1, R.string.Deadpool,
        R.string.DeadpoolInfo,
        "https://iili.io/JMnAfIV.png"
    ),
    Hero(
        2, R.string.IronMan,
        R.string.IronManInfo,
        "https://iili.io/JMnuDI2.png"
    ),
    Hero(
        3, R.string.CaptainAmerica,
        R.string.CaptainAmericaInfo,
        "https://cdn1.ozone.ru/s3/multimedia-8/6502137080.jpg"
    ),
    Hero(
        4, R.string.Spiderman,
        R.string.SpidermanInfo,
        "https://iili.io/JMnuyB9.png"
    ),
    Hero(
        5, R.string.DoctorStrange,
        R.string.DoctorStrangeInfo,
        "https://images.hdqwalls.com/download/artwork-doctor-strange-new-jz-1440x2560.jpg"
    ),
    Hero(
        6, R.string.Thor,
        R.string.ThorInfo,
        "https://images.hdqwalls.com/download/thor-god-of-thunder-2020-qn-1440x2560.jpg"
    ),
    Hero(
        7, R.string.Thanos,
        R.string.ThanosInfo,
        "https://wallpapercosmos.com/w/middle-vertical-retina/5/e/a/464814-1220x2160-phone-hd-thanos-wallpaper-photo.jpg"
    )
)