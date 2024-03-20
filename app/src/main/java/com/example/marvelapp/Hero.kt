package com.example.marvelapp

data class Hero(
    val id: Int,
    val name: Int,
    var image: Int,
    var info: Int,
    val imageUrl : String
)

fun List<Hero>.getHeroById(heroId: Int): Hero? {
    return this.find { it.id == heroId }
}

fun getHeroById(heroId: Int): Hero? {
    return heroes.getHeroById(heroId)
}


val heroes = listOf(
    Hero(
        1, R.string.Deadpool,
        R.drawable.ic_deadpool_card_image,
        R.string.DeadpoolInfo,
        "https://iili.io/JMnAfIV.png"
    ),
    Hero(
        2, R.string.IronMan,
        R.drawable.ic_iron_man,
        R.string.IronManInfo,
        "https://iili.io/JMnuDI2.png"
    ),
    Hero(
        3, R.string.CaptainAmerica,
        R.drawable.captain_america,
        R.string.CaptainAmericaInfo,
        "https://cdn1.ozone.ru/s3/multimedia-8/6502137080.jpg"
    ),
    Hero(
        4, R.string.Spiderman,
        R.drawable.ic_spider_card_image,
        R.string.SpidermanInfo,
        "https://iili.io/JMnuyB9.png"
    ),
    Hero(
        5, R.string.DoctorStrange,
        R.drawable.doctor_strange,
        R.string.DoctorStrangeInfo,
        "https://images.hdqwalls.com/download/artwork-doctor-strange-new-jz-1440x2560.jpg"
    ),
    Hero(
        6, R.string.Thor,
        R.drawable.thor,
        R.string.ThorInfo,
        "https://images.hdqwalls.com/download/thor-god-of-thunder-2020-qn-1440x2560.jpg"
    ),
    Hero(
        7, R.string.Thanos,
        R.drawable.thanos,
        R.string.ThanosInfo,
        "https://wallpapercosmos.com/w/middle-vertical-retina/5/e/a/464814-1220x2160-phone-hd-thanos-wallpaper-photo.jpg"
    )
)