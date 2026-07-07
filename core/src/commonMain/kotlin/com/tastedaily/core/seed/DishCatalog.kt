package com.tastedaily.core.seed

import com.tastedaily.core.model.Article
import com.tastedaily.core.model.ArticleSection
import com.tastedaily.core.model.CookingStep
import com.tastedaily.core.model.Difficulty
import com.tastedaily.core.model.Dish
import com.tastedaily.core.model.Ingredient
import com.tastedaily.core.model.MealType
import com.tastedaily.core.model.VideoAsset
import java.net.URLEncoder

/**
 * Built-in recipe catalog used as the demo data source. In production this would come from a
 * backend that publishes a new dish every day; see [com.tastedaily.core.domain.DailyDishSelector].
 */
object DishCatalog {

    /** Sample MP4 used for cooking-step / preview videos in the demo build. */
    private const val SAMPLE_VIDEO =
        "https://storage.googleapis.com/exoplayer-test-media-0/gen-3/screens/dash-manual/manifest.mpd"

    private fun foodImage(prompt: String): String =
        "https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image" +
            "?prompt=" + URLEncoder.encode("$prompt，中式美食摄影，俯视，暖色调，专业摆盘", "UTF-8") +
            "&image_size=landscape_4_3"

    val all: List<Dish> = listOf(
        // ======================== EXISTING 18 DISHES (mealType = ANY) ========================

        Dish(
            id = "kung_pao_chicken",
            name = "宫保鸡丁",
            tagline = "鲜辣微甜，川味经典下饭神菜",
            cuisine = "川菜",
            coverImageUrl = foodImage("宫保鸡丁"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 25,
            calories = 480,
            ingredients = listOf(
                Ingredient("鸡胸肉", "300 克", 6.0),
                Ingredient("熟花生米", "80 克", 1.5),
                Ingredient("干辣椒", "10 克", 0.3),
                Ingredient("花椒", "3 克", 0.2),
                Ingredient("葱白", "30 克", 0.3),
                Ingredient("生抽", "2 勺", 0.5),
                Ingredient("香醋", "1 勺", 0.2),
                Ingredient("白糖", "1 勺", 0.1),
                Ingredient("淀粉", "1 勺", 0.2),
            ),
            description = "宫保鸡丁是一道享誉海内外的川菜，鸡肉滑嫩、花生酥脆、糊辣荔枝味浓郁。" +
                "讲究旺火快炒、三次下料，做到肉嫩、葱香、花生脆。",
            article = Article(
                title = "宫保鸡丁的前世今生",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection(
                        "起源",
                        "相传清代四川总督丁宝桢爱吃花生与鸡丁，家厨将两者结合并佐以糊辣荔枝味，" +
                            "因丁宝桢被封“太子少保”（宫保），这道菜便得名“宫保鸡丁”。",
                    ),
                    ArticleSection(
                        "灵魂在于“荔枝味型”",
                        "宫保味属于川菜复合味型之一，以生抽、香醋、白糖调成微酸回甜的底味，" +
                            "再以干辣椒、花椒炝锅带出糊辣香，最后勾薄芡让味道裹住每一粒鸡丁。",
                    ),
                    ArticleSection(
                        "三个让花生不软的小窍门",
                        "1）花生临出锅再下；2）鸡丁要浆透锁水；3）兑汁一次倒入快速翻匀即出锅。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "宫保鸡丁完整教学", SAMPLE_VIDEO, foodImage("宫保鸡丁成品"), 596),
                VideoAsset("v2", "怎样切出滑嫩鸡丁", SAMPLE_VIDEO, foodImage("切鸡丁特写"), 180),
            ),
            steps = listOf(
                CookingStep(1, "浆制鸡丁", "鸡胸肉切 1.5cm 方丁，加盐、生抽、料酒、蛋清、淀粉抓匀，封油静置 10 分钟。", 120, foodImage("浆制鸡丁"), null),
                CookingStep(2, "兑碗芡", "生抽、香醋、白糖、盐、淀粉、少许高汤调成荔枝味碗芡备用。", 60, foodImage("碗芡调料"), null),
                CookingStep(3, "炝锅出味", "冷油下花椒小火炸香，再下干辣椒段至棕红，糊辣香气溢出。", 90, foodImage("炝锅辣椒"), SAMPLE_VIDEO),
                CookingStep(4, "滑炒鸡丁", "鸡丁下锅迅速划散，至变色发白、表面微金黄。", 150, foodImage("滑炒鸡丁"), null),
                CookingStep(5, "烹汁合炒", "倒入碗芡快速翻炒，让酱汁裹匀鸡丁，下葱白段翻匀。", 90, foodImage("烹汁翻炒"), null),
                CookingStep(6, "出锅点花生", "关火，撒入熟花生米略翻即可出锅装盘。", 60, foodImage("宫保鸡丁成品"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 25.0,
        ),
        Dish(
            id = "tomato_egg",
            name = "番茄炒蛋",
            tagline = "国民家常菜，三分钟出锅",
            cuisine = "家常菜",
            coverImageUrl = foodImage("番茄炒蛋"),
            difficulty = Difficulty.EASY,
            durationMinutes = 10,
            calories = 280,
            ingredients = listOf(
                Ingredient("鸡蛋", "4 个", 3.0),
                Ingredient("番茄", "2 个（约 300 克）", 3.0),
                Ingredient("葱花", "20 克", 0.3),
                Ingredient("盐", "适量", 0.1),
                Ingredient("白糖", "1 小勺", 0.1),
                Ingredient("食用油", "2 勺", 0.5),
            ),
            description = "番茄炒蛋是中国家庭餐桌上出镜率最高的菜之一，番茄出汁、鸡蛋蓬松，酸甜下饭。",
            article = Article(
                title = "让番茄炒蛋更好吃的细节",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("先炒蛋还是先炒番茄", "先炒蛋。蛋液中火下锅，刚凝固便盛出，避免回锅后变老。"),
                    ArticleSection("番茄要出汁", "番茄下锅加少许盐和糖小火煸炒至出红油汤汁，再回锅鸡蛋吸味。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "番茄炒蛋零失败教学", SAMPLE_VIDEO, foodImage("番茄炒蛋"), 240),
            ),
            steps = listOf(
                CookingStep(1, "打蛋液", "鸡蛋打散，加少许盐和一勺清水打至起泡，炒出来更蓬松。", 60, foodImage("打蛋液"), null),
                CookingStep(2, "炒蛋盛出", "热锅冷油，蛋液下锅迅速推炒至刚凝固，盛出备用。", 90, foodImage("炒鸡蛋"), null),
                CookingStep(3, "炒番茄出汁", "番茄块下锅，加少许盐和糖，小火煸炒至出红油汤汁。", 150, foodImage("炒番茄出汁"), SAMPLE_VIDEO),
                CookingStep(4, "合炒出锅", "鸡蛋回锅翻匀，撒葱花出锅。", 60, foodImage("番茄炒蛋"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 8.0,
        ),
        Dish(
            id = "red_braised_pork",
            name = "红烧肉",
            tagline = "肥而不腻，入口即化的本帮味道",
            cuisine = "本帮菜",
            coverImageUrl = foodImage("红烧肉"),
            difficulty = Difficulty.HARD,
            durationMinutes = 90,
            calories = 620,
            ingredients = listOf(
                Ingredient("带皮五花肉", "500 克", 18.0),
                Ingredient("冰糖", "40 克", 1.0),
                Ingredient("生抽", "3 勺", 0.8),
                Ingredient("老抽", "1 勺", 0.3),
                Ingredient("料酒", "2 勺", 0.5),
                Ingredient("姜片", "20 克", 0.3),
                Ingredient("八角", "2 颗", 0.3),
                Ingredient("热水", "适量", 0.0),
            ),
            description = "红烧肉讲究糖色红亮、肉酥汁浓，肥肉入口即化，瘦肉不柴不塞牙。",
            article = Article(
                title = "一锅好红烧肉的三个关键",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("炒糖色", "冰糖小火慢熬至琥珀色起密集小泡，是红亮不苦的关键。"),
                    ArticleSection("加热水", "炖肉一定要加热水，冷水会让蛋白质收缩变柴。"),
                    ArticleSection("收汁", "最后大火收汁不断翻动，让每块肉都裹上浓稠酱汁。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "红烧肉慢炖全过程", SAMPLE_VIDEO, foodImage("红烧肉"), 900),
                VideoAsset("v2", "怎样炒出完美糖色", SAMPLE_VIDEO, foodImage("炒糖色"), 120),
            ),
            steps = listOf(
                CookingStep(1, "焯水切块", "五花肉冷水下锅，加料酒姜片煮开撇沫，捞出切 3cm 方块。", 300, foodImage("焯水"), null),
                CookingStep(2, "炒糖色", "少许油下冰糖小火熬至琥珀色起小泡。", 180, foodImage("炒糖色"), SAMPLE_VIDEO),
                CookingStep(3, "上色", "肉块下锅迅速翻炒，让每块肉裹上糖色。", 120, foodImage("红烧肉上色"), null),
                CookingStep(4, "调味炖煮", "加生抽、老抽、料酒、八角、姜片，倒入没过肉的热水，小火炖 50 分钟。", 3000, foodImage("炖煮红烧肉"), null),
                CookingStep(5, "收汁装盘", "大火收汁至浓稠，翻匀装盘。", 300, foodImage("红烧肉"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 30.0,
        ),
        Dish(
            id = "mapo_tofu",
            name = "麻婆豆腐",
            tagline = "麻、辣、烫、香、酥、嫩、鲜",
            cuisine = "川菜",
            coverImageUrl = foodImage("麻婆豆腐"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 20,
            calories = 360,
            ingredients = listOf(
                Ingredient("嫩豆腐", "1 盒（约 400 克）", 3.0),
                Ingredient("牛肉末", "100 克", 5.0),
                Ingredient("郫县豆瓣酱", "1.5 勺", 1.0),
                Ingredient("豆豉", "1 勺", 0.5),
                Ingredient("花椒粉", "适量", 0.3),
                Ingredient("蒜苗", "30 克", 0.5),
                Ingredient("水淀粉", "适量", 0.2),
            ),
            description = "麻婆豆腐讲究“八字真言”：麻、辣、烫、香、酥、嫩、鲜、活，是川菜代表。",
            article = Article(
                title = "麻婆豆腐的“八字真言”",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("麻辣之源", "花椒的“麻”与郫县豆瓣的“辣”共同构成麻婆豆腐的灵魂底味。"),
                    ArticleSection("豆腐不碎", "豆腐先焯水去豆腥并定型，下锅后轻推不铲，三次勾薄芡让味挂住。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "麻婆豆腐完整教学", SAMPLE_VIDEO, foodImage("麻婆豆腐"), 360),
            ),
            steps = listOf(
                CookingStep(1, "豆腐焯水", "豆腐切 2cm 方块，淡盐水焯 1 分钟捞出沥干。", 120, foodImage("豆腐焯水"), null),
                CookingStep(2, "炒牛肉末", "牛肉末小火煸至酥香出油。", 120, foodImage("炒牛肉末"), null),
                CookingStep(3, "炒豆瓣红油", "下豆瓣酱、豆豉剁碎炒出红油，加蒜末姜末。", 90, foodImage("炒豆瓣红油"), SAMPLE_VIDEO),
                CookingStep(4, "烧豆腐", "加高汤、豆腐，中火烧 3 分钟，分三次勾薄芡。", 240, foodImage("烧豆腐"), null),
                CookingStep(5, "出锅点花椒", "盛碗，撒花椒粉和蒜苗末。", 30, foodImage("麻婆豆腐"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 15.0,
        ),
        Dish(
            id = "yu_xiang_pork",
            name = "鱼香肉丝",
            tagline = "吃鱼不见鱼，鱼香惹人馋",
            cuisine = "川菜",
            coverImageUrl = foodImage("鱼香肉丝"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 20,
            calories = 380,
            ingredients = listOf(
                Ingredient("猪里脊", "250 克", 8.0),
                Ingredient("水发木耳", "60 克", 1.0),
                Ingredient("冬笋", "80 克", 2.0),
                Ingredient("泡红椒", "30 克", 0.5),
                Ingredient("葱姜蒜末", "共 30 克", 0.5),
                Ingredient("生抽", "2 勺", 0.5),
                Ingredient("香醋", "3 勺", 0.5),
                Ingredient("白糖", "2 勺", 0.2),
            ),
            description = "鱼香肉丝没有鱼，靠泡椒、糖醋调出“鱼香”复合味，是川菜名馔。",
            article = Article(
                title = "鱼香味是怎么来的",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("没有鱼的鱼香", "泡椒茸与葱姜蒜爆出香气，配合糖醋比例（糖醋约 2:3），模拟出类似泡菜鱼的鲜香。"),
                    ArticleSection("丝要均匀", "里脊、木耳、冬笋都切细丝，成熟一致、口感统一。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "鱼香肉丝教学", SAMPLE_VIDEO, foodImage("鱼香肉丝"), 300),
            ),
            steps = listOf(
                CookingStep(1, "切丝浆肉", "里脊切细丝，加盐、料酒、水淀粉抓匀；冬笋、木耳切丝。", 180, foodImage("切丝"), null),
                CookingStep(2, "兑鱼香汁", "生抽、香醋、白糖、盐、水淀粉、高汤调成鱼香碗芡。", 60, foodImage("鱼香汁"), null),
                CookingStep(3, "炒泡椒", "热油下泡椒茸、姜蒜末炒红出香。", 90, foodImage("炒泡椒"), SAMPLE_VIDEO),
                CookingStep(4, "滑炒肉丝", "肉丝下锅划散变色。", 90, foodImage("滑炒肉丝"), null),
                CookingStep(5, "合炒烹汁", "下笋丝木耳丝翻炒，倒入鱼香汁翻匀出锅。", 90, foodImage("鱼香肉丝"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 18.0,
        ),
        Dish(
            id = "scallion_noodles",
            name = "葱油拌面",
            tagline = "一把面条一勺葱油，简单到极致",
            cuisine = "沪式面点",
            coverImageUrl = foodImage("葱油拌面"),
            difficulty = Difficulty.EASY,
            durationMinutes = 30,
            calories = 410,
            ingredients = listOf(
                Ingredient("细面条", "200 克", 2.0),
                Ingredient("小葱", "150 克", 1.0),
                Ingredient("食用油", "100 毫升", 1.0),
                Ingredient("生抽", "3 勺", 0.8),
                Ingredient("老抽", "1 勺", 0.3),
                Ingredient("白糖", "1.5 勺", 0.2),
            ),
            description = "葱油拌面是上海人的灵魂 comfort food，炸得焦香的葱油裹住每根面条，咸甜交织。",
            article = Article(
                title = "一勺葱油的修行",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("慢火炸葱", "小葱切段，冷油下锅小火慢炸，至葱叶焦黄酥脆，香气最浓时离火。"),
                    ArticleSection("酱油激香", "趁葱油还热，倒入生抽老抽白糖，余温会把酱汁激出复合香气。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "葱油拌面完整教学", SAMPLE_VIDEO, foodImage("葱油拌面"), 240),
            ),
            steps = listOf(
                CookingStep(1, "炸葱油", "小葱切段，冷油下锅小火慢炸至焦黄酥脆，捞出葱段。", 600, foodImage("炸葱油"), SAMPLE_VIDEO),
                CookingStep(2, "调葱油酱", "葱油中加生抽、老抽、白糖小火煮至糖化。", 120, foodImage("葱油酱"), null),
                CookingStep(3, "煮面", "面条煮至刚断生，捞出过一下温水沥干。", 240, foodImage("煮面"), null),
                CookingStep(4, "拌面", "面条倒入碗中，淋葱油酱翻拌均匀，撒焦葱段。", 60, foodImage("葱油拌面"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 6.0,
        ),
        Dish(
            id = "pork_belly_potato",
            name = "五花肉炒土豆片",
            tagline = "焦香下饭，土豆吸饱肉香",
            cuisine = "家常菜",
            coverImageUrl = foodImage("五花肉炒土豆片"),
            difficulty = Difficulty.EASY,
            durationMinutes = 25,
            calories = 420,
            ingredients = listOf(
                Ingredient("五花肉", "250 克", 8.0),
                Ingredient("土豆", "2 个（约 300 克）", 2.0),
                Ingredient("青红椒", "各 1 个", 1.0),
                Ingredient("蒜片", "15 克", 0.3),
                Ingredient("生抽", "2 勺", 0.5),
                Ingredient("老抽", "半勺", 0.2),
                Ingredient("盐", "适量", 0.1),
                Ingredient("食用油", "1 勺", 0.3),
            ),
            description = "五花肉煸出油脂，土豆片吸饱肉香，煎至微焦金黄，是最朴实的下饭菜。",
            article = Article(
                title = "让土豆片不粘连的窍门",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("土豆要泡水", "切好的土豆片用清水泡去淀粉，沥干再下锅，煎炒才不粘不散。"),
                    ArticleSection("先煸肉出油", "五花肉小火慢慢煸出油脂，用猪油炒土豆片更香更润。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "五花肉炒土豆片教学", SAMPLE_VIDEO, foodImage("五花肉炒土豆片"), 300),
            ),
            steps = listOf(
                CookingStep(1, "备料", "五花肉切薄片，土豆切薄片泡水沥干，青红椒切块。", 180, foodImage("切土豆片"), null),
                CookingStep(2, "煸五花肉", "冷锅下五花肉小火煸至卷曲出油、边缘微焦。", 180, foodImage("煸五花肉"), SAMPLE_VIDEO),
                CookingStep(3, "炒土豆片", "下土豆片中火煎炒至两面微黄断生。", 240, foodImage("煎土豆片"), null),
                CookingStep(4, "调味出锅", "加生抽、老抽、蒜片、青红椒翻炒断生，调盐出锅。", 90, foodImage("五花肉炒土豆片"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 15.0,
        ),
        Dish(
            id = "red_braised_fish",
            name = "红烧鱼块",
            tagline = "酱汁浓稠，鱼肉鲜嫩入味",
            cuisine = "家常菜",
            coverImageUrl = foodImage("红烧鱼块"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 35,
            calories = 320,
            ingredients = listOf(
                Ingredient("草鱼中段", "600 克", 15.0),
                Ingredient("姜片", "20 克", 0.3),
                Ingredient("葱段", "30 克", 0.3),
                Ingredient("蒜末", "10 克", 0.2),
                Ingredient("生抽", "2 勺", 0.5),
                Ingredient("老抽", "1 勺", 0.3),
                Ingredient("料酒", "2 勺", 0.5),
                Ingredient("白糖", "1 勺", 0.1),
                Ingredient("香醋", "半勺", 0.1),
            ),
            description = "红烧鱼块酱色红亮、鱼肉细嫩，关键是煎鱼不破皮、炖煮入味收汁浓稠。",
            article = Article(
                title = "煎鱼不破皮的两个要点",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("热锅凉油", "锅烧到冒青烟再倒油，鱼块擦干水分下锅，中火煎至定型再翻面。"),
                    ArticleSection("醋去腥", "烹入少许香醋随蒸汽带走腥味，是红烧鱼块不腥的关键。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "红烧鱼块完整教学", SAMPLE_VIDEO, foodImage("红烧鱼块"), 420),
            ),
            steps = listOf(
                CookingStep(1, "处理鱼块", "草鱼中段切块，加盐、料酒腌制 10 分钟，擦干水分。", 600, foodImage("鱼块腌制"), null),
                CookingStep(2, "煎鱼块", "热锅凉油下鱼块，中火煎至两面金黄定型。", 360, foodImage("煎鱼块"), SAMPLE_VIDEO),
                CookingStep(3, "炝锅调味", "下姜片、葱段、蒜末爆香，烹料酒、香醋，加生抽、老抽、白糖。", 90, foodImage("炝锅调味"), null),
                CookingStep(4, "炖煮收汁", "加开水没过鱼块一半，中火炖 8 分钟，大火收汁浓稠。", 540, foodImage("红烧鱼块"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 25.0,
        ),
        Dish(
            id = "tomato_meatball_soup",
            name = "番茄汽水丸子汤",
            tagline = "酸甜开胃，丸子Q弹多汁",
            cuisine = "家常菜",
            coverImageUrl = foodImage("番茄汽水丸子汤"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 30,
            calories = 290,
            ingredients = listOf(
                Ingredient("猪肉末", "250 克", 8.0),
                Ingredient("番茄", "3 个", 4.0),
                Ingredient("蛋清", "1 个", 0.8),
                Ingredient("淀粉", "1 勺", 0.2),
                Ingredient("姜葱水", "3 勺", 0.3),
                Ingredient("盐", "适量", 0.1),
                Ingredient("白糖", "1 勺", 0.1),
                Ingredient("香醋", "少许", 0.1),
            ),
            description = "番茄煮出酸甜汤汁，手打肉丸滑嫩Q弹，加一勺汽水让汤底更清爽蓬松，开胃解腻。",
            article = Article(
                title = "丸子Q弹的秘密",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("顺方向搅打上劲", "肉末加姜葱水、蛋清、淀粉顺一个方向搅打至上劲发黏，丸子才Q弹不散。"),
                    ArticleSection("汽水点睛", "汤底淋入少许汽水，气泡让丸子更蓬松，也带出一丝清新酸甜。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "番茄丸子汤教学", SAMPLE_VIDEO, foodImage("番茄汽水丸子汤"), 360),
            ),
            steps = listOf(
                CookingStep(1, "调肉馅", "肉末加姜葱水、蛋清、淀粉、盐顺方向搅打上劲。", 300, foodImage("调肉馅"), null),
                CookingStep(2, "炒番茄汤底", "番茄去皮切块，小火煸炒出红油汤汁，加开水煮沸。", 240, foodImage("炒番茄汤底"), SAMPLE_VIDEO),
                CookingStep(3, "下丸子", "转小火，用勺挖肉丸入汤，定型后不搅动。", 300, foodImage("下丸子"), null),
                CookingStep(4, "调味出锅", "加盐、白糖调味，淋少许香醋和汽水，撒葱花出锅。", 60, foodImage("番茄汽水丸子汤"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 18.0,
        ),
        Dish(
            id = "hand_torn_cabbage",
            name = "手撕包菜",
            tagline = "脆爽开胃，三分钟出锅",
            cuisine = "湘菜",
            coverImageUrl = foodImage("手撕包菜"),
            difficulty = Difficulty.EASY,
            durationMinutes = 10,
            calories = 180,
            ingredients = listOf(
                Ingredient("包菜", "1 个（约 500 克）", 4.0),
                Ingredient("干辣椒", "5 个", 0.3),
                Ingredient("蒜末", "15 克", 0.3),
                Ingredient("五花肉", "50 克", 2.0),
                Ingredient("生抽", "1 勺", 0.3),
                Ingredient("香醋", "1 勺", 0.2),
                Ingredient("盐", "适量", 0.1),
            ),
            description = "手撕的包菜断面不规则更入味，旺火爆炒断生即出锅，脆爽酸辣最下饭。",
            article = Article(
                title = "为什么手撕比刀切好吃",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("手撕更入味", "手撕断面参差不齐，比刀切更易吸附调料，口感也更脆。"),
                    ArticleSection("旺火快炒", "全程大火，包菜下锅不超过两分钟，断生即出锅才能保持脆爽。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "手撕包菜教学", SAMPLE_VIDEO, foodImage("手撕包菜"), 180),
            ),
            steps = listOf(
                CookingStep(1, "手撕包菜", "包菜去硬芯，用手撕成大片，洗净沥干水分。", 120, foodImage("手撕包菜"), null),
                CookingStep(2, "煸肉出油", "五花肉切片小火煸出油，下干辣椒、蒜末爆香。", 60, foodImage("煸肉爆香"), SAMPLE_VIDEO),
                CookingStep(3, "旺火爆炒", "大火下包菜快速翻炒，沿锅边淋生抽、香醋。", 90, foodImage("爆炒包菜"), null),
                CookingStep(4, "出锅", "加盐调味，翻匀断生立即出锅。", 30, foodImage("手撕包菜"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 8.0,
        ),
        Dish(
            id = "chestnut_chicken",
            name = "板栗烧鸡",
            tagline = "板栗粉糯，鸡肉鲜香",
            cuisine = "家常菜",
            coverImageUrl = foodImage("板栗烧鸡"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 45,
            calories = 480,
            ingredients = listOf(
                Ingredient("鸡腿肉", "500 克", 15.0),
                Ingredient("去壳板栗", "200 克", 8.0),
                Ingredient("姜片", "20 克", 0.3),
                Ingredient("葱段", "30 克", 0.3),
                Ingredient("生抽", "2 勺", 0.5),
                Ingredient("老抽", "1 勺", 0.3),
                Ingredient("料酒", "2 勺", 0.5),
                Ingredient("冰糖", "15 克", 0.5),
                Ingredient("八角", "1 颗", 0.2),
            ),
            description = "板栗烧鸡是秋冬经典，板栗吸饱鸡汤粉糯香甜，鸡肉酱香浓郁，一锅端最满足。",
            article = Article(
                title = "板栗不碎的技巧",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("板栗晚下锅", "鸡肉先炖 15 分钟入味，再下板栗同炖，避免板栗久煮碎烂。"),
                    ArticleSection("过油更香", "板栗先过油炸至微黄，外层定型内里粉糯，炖煮不易散。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "板栗烧鸡完整教学", SAMPLE_VIDEO, foodImage("板栗烧鸡"), 540),
            ),
            steps = listOf(
                CookingStep(1, "备料", "鸡腿剁块，板栗去壳去皮，姜切片，葱切段。", 300, foodImage("备料"), null),
                CookingStep(2, "煎板栗", "板栗过油中火煎至表面微黄捞出。", 180, foodImage("煎板栗"), SAMPLE_VIDEO),
                CookingStep(3, "炒鸡块", "鸡块下锅煸炒至变色，加姜片、葱段、八角、冰糖炒上色。", 240, foodImage("炒鸡块"), null),
                CookingStep(4, "炖煮", "加生抽、老抽、料酒、热水没过鸡块，小火炖 15 分钟。", 900, foodImage("炖煮"), null),
                CookingStep(5, "下板栗收汁", "下板栗同炖 10 分钟，大火收汁浓稠出锅。", 600, foodImage("板栗烧鸡"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 30.0,
        ),
        Dish(
            id = "sweet_sour_ribs",
            name = "糖醋排骨",
            tagline = "酸甜适口，色泽红亮",
            cuisine = "家常菜",
            coverImageUrl = foodImage("糖醋排骨"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 50,
            calories = 520,
            ingredients = listOf(
                Ingredient("小排", "500 克", 22.0),
                Ingredient("生抽", "2 勺", 0.5),
                Ingredient("香醋", "3 勺", 0.5),
                Ingredient("白糖", "3 勺", 0.3),
                Ingredient("料酒", "2 勺", 0.5),
                Ingredient("姜片", "15 克", 0.3),
                Ingredient("葱段", "20 克", 0.3),
                Ingredient("白芝麻", "少许", 0.3),
            ),
            description = "糖醋排骨讲究糖醋比例黄金 1:1，排骨外焦里嫩，酱汁裹匀红亮挂霜，酸甜开胃。",
            article = Article(
                title = "黄金糖醋比例",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("1:1:1 口诀", "生抽、香醋、白糖各一勺为一份，按排骨量等比放大，是糖醋味零失败的口诀。"),
                    ArticleSection("收汁要翻动", "大火收汁时不断翻动排骨，让酱汁均匀挂上，出锅前撒芝麻增香。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "糖醋排骨完整教学", SAMPLE_VIDEO, foodImage("糖醋排骨"), 600),
            ),
            steps = listOf(
                CookingStep(1, "焯水", "小排冷水下锅加料酒姜片煮开撇沫，捞出洗净。", 300, foodImage("焯水"), null),
                CookingStep(2, "炖排骨", "排骨加水、姜片、葱段小火炖 30 分钟至软烂，捞出沥干。", 1800, foodImage("炖排骨"), null),
                CookingStep(3, "煎排骨", "少许油下排骨中火煎至两面微焦。", 240, foodImage("煎排骨"), SAMPLE_VIDEO),
                CookingStep(4, "调糖醋汁", "生抽、香醋、白糖按 1:1:1 调成碗芡倒入锅中。", 60, foodImage("糖醋汁"), null),
                CookingStep(5, "收汁出锅", "中火收汁至浓稠挂匀，撒白芝麻出锅。", 180, foodImage("糖醋排骨"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 28.0,
        ),
        Dish(
            id = "mala_xiangguo",
            name = "麻辣香锅",
            tagline = "一锅烩万物，麻辣过瘾",
            cuisine = "川菜",
            coverImageUrl = foodImage("麻辣香锅"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 40,
            calories = 560,
            ingredients = listOf(
                Ingredient("虾", "200 克", 12.0),
                Ingredient("藕片", "150 克", 1.5),
                Ingredient("土豆片", "150 克", 1.0),
                Ingredient("午餐肉", "150 克", 5.0),
                Ingredient("花菜", "150 克", 2.0),
                Ingredient("火锅底料", "50 克", 3.0),
                Ingredient("干辣椒", "15 克", 0.5),
                Ingredient("花椒", "5 克", 0.3),
                Ingredient("蒜末", "20 克", 0.3),
                Ingredient("白芝麻", "少许", 0.3),
            ),
            description = "麻辣香锅把各种食材一锅烩炒，火锅底料打底，麻辣鲜香，荤素自搭配最过瘾。",
            article = Article(
                title = "香锅不水塌的要点",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("食材先焯熟", "根茎类食材先焯水至八成熟，下锅只需裹味，避免出水让香锅变汤锅。"),
                    ArticleSection("底料要炒香", "火锅底料小火炒出红油再下食材，香味才能均匀裹住每一块。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "麻辣香锅完整教学", SAMPLE_VIDEO, foodImage("麻辣香锅"), 480),
            ),
            steps = listOf(
                CookingStep(1, "备料焯水", "藕片、土豆片、花菜焯水至八成熟捞出沥干。", 360, foodImage("焯水食材"), null),
                CookingStep(2, "炒底料", "小火下火锅底料、干辣椒、花椒炒出红油香气。", 120, foodImage("炒底料"), SAMPLE_VIDEO),
                CookingStep(3, "炒虾午餐肉", "下虾和午餐肉翻炒至虾变红。", 180, foodImage("炒虾"), null),
                CookingStep(4, "烩炒全部食材", "下所有焯好的蔬菜大火翻炒，让底料裹匀。", 180, foodImage("烩炒"), null),
                CookingStep(5, "出锅", "加蒜末、白芝麻翻匀出锅。", 60, foodImage("麻辣香锅"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 35.0,
        ),
        Dish(
            id = "meicai_kourou",
            name = "梅菜扣肉",
            tagline = "肉烂味浓，梅菜吸油增香",
            cuisine = "客家菜",
            coverImageUrl = foodImage("梅菜扣肉"),
            difficulty = Difficulty.HARD,
            durationMinutes = 120,
            calories = 680,
            ingredients = listOf(
                Ingredient("带皮五花肉", "600 克", 22.0),
                Ingredient("梅干菜", "100 克", 3.0),
                Ingredient("生抽", "3 勺", 0.8),
                Ingredient("老抽", "2 勺", 0.5),
                Ingredient("蚝油", "1 勺", 0.5),
                Ingredient("冰糖", "20 克", 0.5),
                Ingredient("料酒", "2 勺", 0.5),
                Ingredient("姜片", "20 克", 0.3),
            ),
            description = "梅菜扣肉是客家名菜，五花肉蒸至入口即化，梅菜吸饱肉油咸香回甘，肥而不腻。",
            article = Article(
                title = "扣肉皮起虎纹的秘诀",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("炸皮起虎纹", "五花肉煮后扎孔涂老抽，下油锅炸至肉皮起泡虎纹，蒸出来才软糯好看。"),
                    ArticleSection("梅菜要洗透", "梅干菜反复清洗去沙，加糖油炒香再垫底，蒸制时油香渗入才好吃。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "梅菜扣肉完整教学", SAMPLE_VIDEO, foodImage("梅菜扣肉"), 1200),
            ),
            steps = listOf(
                CookingStep(1, "煮肉", "五花肉冷水下锅加料酒姜片煮 20 分钟至筷子能插入。", 1200, foodImage("煮肉"), null),
                CookingStep(2, "炸皮", "肉皮扎孔涂老抽，下油锅炸至肉皮金黄起虎纹，泡冷水。", 360, foodImage("炸皮"), SAMPLE_VIDEO),
                CookingStep(3, "切肉腌制", "切 0.8cm 厚片，加生抽、老抽、蚝油、冰糖拌匀腌 20 分钟。", 1200, foodImage("腌制肉片"), null),
                CookingStep(4, "炒梅菜", "梅干菜洗净切碎，加少许糖油炒香垫碗底。", 240, foodImage("炒梅菜"), null),
                CookingStep(5, "扣蒸", "肉片皮朝下码在梅菜上，大火蒸 90 分钟。", 5400, foodImage("扣蒸"), null),
                CookingStep(6, "翻扣出锅", "蒸好后倒扣出盘，浇原汁即可。", 60, foodImage("梅菜扣肉"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 32.0,
        ),
        Dish(
            id = "wanzhou_grilled_fish",
            name = "万州烤鱼（草鱼）",
            tagline = "先烤后炖，麻辣鲜香一锅端",
            cuisine = "川菜",
            coverImageUrl = foodImage("万州烤鱼"),
            difficulty = Difficulty.HARD,
            durationMinutes = 70,
            calories = 580,
            ingredients = listOf(
                Ingredient("草鱼", "1 条（约 1000 克）", 25.0),
                Ingredient("藕片", "200 克", 2.0),
                Ingredient("豆皮", "150 克", 1.5),
                Ingredient("魔芋", "150 克", 2.0),
                Ingredient("郫县豆瓣酱", "2 勺", 1.0),
                Ingredient("干辣椒", "20 克", 0.5),
                Ingredient("花椒", "10 克", 0.5),
                Ingredient("蒜末", "20 克", 0.3),
                Ingredient("孜然粉", "1 勺", 0.3),
                Ingredient("料酒", "2 勺", 0.5),
            ),
            description = "万州烤鱼先腌后烤再炖，鱼皮焦香鱼肉嫩滑，配菜吸饱麻辣汤汁，一锅端最过瘾。",
            article = Article(
                title = "烤鱼不散的要点",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("从背开鱼", "草鱼从背部剖开不切断腹部，展开成片更易烤透，翻面也不散。"),
                    ArticleSection("先烤后炖", "鱼先烤至皮焦七八成熟，再浇汤汁小火炖，外焦里嫩不碎。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "万州烤鱼完整教学", SAMPLE_VIDEO, foodImage("万州烤鱼"), 840),
            ),
            steps = listOf(
                CookingStep(1, "处理腌制", "草鱼从背剖开，加料酒、盐、孜然粉、姜片腌制 20 分钟。", 1200, foodImage("腌制草鱼"), null),
                CookingStep(2, "烤鱼", "鱼摊开刷油，入烤箱 220 度烤 20 分钟至皮焦。", 1200, foodImage("烤鱼"), SAMPLE_VIDEO),
                CookingStep(3, "炒底料配菜", "下豆瓣酱、干辣椒、花椒、蒜末炒红油，加藕片、豆皮、魔芋翻炒。", 240, foodImage("炒底料配菜"), null),
                CookingStep(4, "炖煮", "烤鱼放配菜上，加少许水小火炖 10 分钟入味。", 600, foodImage("炖煮烤鱼"), null),
                CookingStep(5, "出锅", "撒孜然粉、干辣椒段，浇热油激香出锅。", 60, foodImage("万州烤鱼"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 45.0,
        ),
        Dish(
            id = "steamed_pork_potato",
            name = "粉蒸五花肉土豆",
            tagline = "米粉裹肉，土豆垫底吸香",
            cuisine = "家常菜",
            coverImageUrl = foodImage("粉蒸五花肉土豆"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 60,
            calories = 460,
            ingredients = listOf(
                Ingredient("五花肉", "400 克", 15.0),
                Ingredient("土豆", "2 个", 2.0),
                Ingredient("蒸肉米粉", "150 克", 2.0),
                Ingredient("生抽", "2 勺", 0.5),
                Ingredient("老抽", "1 勺", 0.3),
                Ingredient("豆瓣酱", "1 勺", 0.5),
                Ingredient("料酒", "1 勺", 0.3),
                Ingredient("姜末", "10 克", 0.2),
            ),
            description = "粉蒸肉米粉裹住五花肉，蒸出油脂被土豆吸收，肉烂米香，土豆绵软入味。",
            article = Article(
                title = "蒸肉米粉的讲究",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("米粉自制更香", "大米加八角花椒小火炒至微黄，打碎成粗颗粒，比买的更香更有嚼劲。"),
                    ArticleSection("土豆垫底", "土豆切块垫碗底，蒸制时吸收肉汁油脂，比肉还抢手。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "粉蒸五花肉教学", SAMPLE_VIDEO, foodImage("粉蒸五花肉土豆"), 720),
            ),
            steps = listOf(
                CookingStep(1, "腌制肉片", "五花肉切 0.5cm 片，加生抽、老抽、豆瓣酱、料酒、姜末腌 20 分钟。", 1200, foodImage("腌制肉片"), null),
                CookingStep(2, "裹米粉", "腌好的肉片裹满蒸肉米粉，加少许水拌匀让米粉湿润。", 180, foodImage("裹米粉"), SAMPLE_VIDEO),
                CookingStep(3, "码碗", "土豆切块垫碗底，肉片码在土豆上方。", 120, foodImage("码碗"), null),
                CookingStep(4, "上锅蒸", "大火蒸 40 分钟至肉烂土豆软。", 2400, foodImage("蒸制"), null),
                CookingStep(5, "出锅", "倒扣出盘，撒葱花即可。", 30, foodImage("粉蒸五花肉土豆"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 20.0,
        ),
        Dish(
            id = "steamed_wuchang_fish",
            name = "清蒸武昌鱼",
            tagline = "原汁原味，鲜嫩清甜",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("清蒸武昌鱼"),
            difficulty = Difficulty.EASY,
            durationMinutes = 20,
            calories = 240,
            ingredients = listOf(
                Ingredient("武昌鱼", "1 条（约 500 克）", 25.0),
                Ingredient("葱丝", "20 克", 0.3),
                Ingredient("姜丝", "20 克", 0.3),
                Ingredient("红椒丝", "10 克", 0.3),
                Ingredient("蒸鱼豉油", "3 勺", 1.0),
                Ingredient("料酒", "1 勺", 0.3),
                Ingredient("食用油", "2 勺", 0.5),
            ),
            description = "清蒸武昌鱼讲究火候精准，鱼刚断生即出锅，淋上热油激香葱丝，肉质细嫩清甜。",
            article = Article(
                title = "清蒸鱼的火候",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("上汽再入锅", "蒸锅水烧开上汽后再放鱼，高温快速锁住鲜味，鱼肉不腥不老。"),
                    ArticleSection("热油激香", "出锅后铺葱丝姜丝，浇滚烫热油激出香气，再淋蒸鱼豉油。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "清蒸武昌鱼教学", SAMPLE_VIDEO, foodImage("清蒸武昌鱼"), 300),
            ),
            steps = listOf(
                CookingStep(1, "处理鱼", "武昌鱼去鳞去内脏洗净，两面划刀，抹料酒姜片去腥。", 300, foodImage("处理鱼"), null),
                CookingStep(2, "上锅蒸", "水烧上汽，鱼盘放蒸锅大火蒸 8 分钟。", 480, foodImage("蒸鱼"), SAMPLE_VIDEO),
                CookingStep(3, "铺葱丝", "倒掉盘中蒸汁，铺上葱丝、姜丝、红椒丝。", 60, foodImage("铺葱丝"), null),
                CookingStep(4, "浇油淋汁", "热油浇在葱丝上激香，淋蒸鱼豉油即可。", 60, foodImage("清蒸武昌鱼"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 30.0,
        ),
        Dish(
            id = "stir_fried_okra",
            name = "清炒秋葵",
            tagline = "清爽脆嫩，三分钟上桌",
            cuisine = "家常菜",
            coverImageUrl = foodImage("清炒秋葵"),
            difficulty = Difficulty.EASY,
            durationMinutes = 10,
            calories = 120,
            ingredients = listOf(
                Ingredient("秋葵", "300 克", 5.0),
                Ingredient("蒜末", "15 克", 0.3),
                Ingredient("盐", "适量", 0.1),
                Ingredient("食用油", "1 勺", 0.3),
                Ingredient("生抽", "半勺", 0.2),
            ),
            description = "清炒秋葵翠绿脆嫩，保留黏液营养，蒜香清爽，是低卡健康的快手家常菜。",
            article = Article(
                title = "秋葵翠绿不发黑的窍门",
                author = "味道日历编辑部",
                sections = listOf(
                    ArticleSection("先焯水", "秋葵整根下沸水焯 30 秒捞出再切，可减少黏液流失，颜色更翠绿。"),
                    ArticleSection("整根焯再切", "先切后焯会让黏液和营养大量流失，整根焯好再切段最佳。"),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "清炒秋葵教学", SAMPLE_VIDEO, foodImage("清炒秋葵"), 180),
            ),
            steps = listOf(
                CookingStep(1, "焯水", "秋葵整根下沸水焯 30 秒捞出过凉，切斜段。", 60, foodImage("焯秋葵"), null),
                CookingStep(2, "炝锅", "热油下蒜末爆香。", 30, foodImage("炝锅"), SAMPLE_VIDEO),
                CookingStep(3, "快炒出锅", "下秋葵大火快炒 1 分钟，加盐、生抽翻匀出锅。", 60, foodImage("清炒秋葵"), null),
            ),
            mealType = MealType.ANY,
            estimatedCostYuan = 8.0,
        ),

        // ======================== WUHAN BREAKFAST DISHES (mealType = BREAKFAST) ========================

        Dish(
            id = "hot_dry_noodles",
            name = "热干面",
            tagline = "武汉过早的灵魂担当，碱水面拌芝麻酱，筋道香浓",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("热干面"),
            difficulty = Difficulty.EASY,
            durationMinutes = 15,
            calories = 450,
            ingredients = listOf(
                Ingredient("碱水面", "200g", 3.0),
                Ingredient("芝麻酱", "30g", 2.0),
                Ingredient("香油", "10ml", 0.5),
                Ingredient("辣萝卜丁", "20g", 1.0),
                Ingredient("葱花", "10g", 0.2),
            ),
            description = "热干面是武汉过早的灵魂担当，碱水面拌上浓郁芝麻酱，筋道香浓。" +
                "一碗下肚满口芝麻香气，是武汉人一天开始的仪式感。" +
                "配上辣萝卜丁和葱花，层次分明回味无穷。",
            article = Article(
                title = "热干面：武汉人的过早信仰",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "从码头到早点的百年演变",
                        "热干面起源于上世纪三十年代的汉口码头，工人需要一种快捷耐饿的早餐。" +
                            "黄碱面煮熟后拌油摊凉，吃时用滚水汆烫，浇上芝麻酱即可，既饱腹又方便。",
                    ),
                    ArticleSection(
                        "芝麻酱是灵魂",
                        "正宗热干面必用纯芝麻酱，加香油稀释至能拉丝的稠度。" +
                            "面条要选碱水面，口感筋道不软烂，拌酱时讲究三拌三挑让每根面条都裹上酱。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "热干面完整教学", SAMPLE_VIDEO, foodImage("热干面"), 300),
            ),
            steps = listOf(
                CookingStep(1, "煮面摊凉", "碱水面下锅煮至八成熟，捞出拌食用油摊凉备用。", 300, null, null),
                CookingStep(2, "烫面", "面条用滚水快速汆烫10秒，甩干水分装入碗中。", 60, null, null),
                CookingStep(3, "调芝麻酱", "芝麻酱加香油、少许温水搅至顺滑能拉丝。", 120, null, null),
                CookingStep(4, "拌面调味", "面条浇芝麻酱，加生抽、辣萝卜丁、葱花拌匀即可。", 60, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 8.0,
        ),
        Dish(
            id = "san_xian_dou_pi",
            name = "三鲜豆皮",
            tagline = "金黄酥脆的碳水炸弹，老通城百年老字号",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("三鲜豆皮"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 30,
            calories = 550,
            ingredients = listOf(
                Ingredient("绿豆大米混合粉", "150g", 2.0),
                Ingredient("糯米", "100g", 1.0),
                Ingredient("鲜笋", "30g", 1.5),
                Ingredient("香菇", "30g", 1.0),
                Ingredient("肉丁", "50g", 3.0),
                Ingredient("鸡蛋", "2个", 2.0),
            ),
            description = "三鲜豆皮是老通城百年老字号的招牌，金黄酥脆的豆皮包裹糯米和鲜肉馅，外脆内糯。" +
                "一口咬下，蛋皮焦香、糯米软糯、馅料鲜香三重口感交织。" +
                "是武汉过早中最有分量的碳水炸弹。",
            article = Article(
                title = "老通城豆皮的百年传承",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "皮薄如纸有秘诀",
                        "豆皮用绿豆和大米混合磨浆摊成，比纯米浆更脆更香。" +
                            "煎制时用大铁锅小火慢煎，蛋皮金黄微焦才酥脆，翻面靠老师傅一抖手腕的功夫。",
                    ),
                    ArticleSection(
                        "三鲜馅料讲究",
                        "三鲜指鲜肉、鲜笋、鲜菇，切丁炒香后与糯米饭拌在一起。" +
                            "糯米要蒸至粒粒分明不过烂，馅料咸鲜适口，与脆皮形成绝妙对比。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "三鲜豆皮完整教学", SAMPLE_VIDEO, foodImage("三鲜豆皮"), 600),
            ),
            steps = listOf(
                CookingStep(1, "磨浆摊皮", "绿豆大米混合磨浆，大锅摊成薄饼，打入鸡蛋摊匀煎至金黄。", 300, null, null),
                CookingStep(2, "蒸糯米", "糯米浸泡后蒸至八成熟，粒粒分明。", 600, null, null),
                CookingStep(3, "炒馅料", "肉丁、鲜笋、香菇丁炒香调味，与糯米饭拌匀。", 300, null, null),
                CookingStep(4, "铺馅翻面", "糯米馅料铺在豆皮上，大锅翻面煎至两面酥脆。", 300, null, null),
                CookingStep(5, "切块装盘", "切成小块装盘，淋少许香油即可。", 60, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 12.0,
        ),
        Dish(
            id = "mian_wo",
            name = "面窝",
            tagline = "中间薄脆、边沿厚实的油炸米饼，咔嚓作响",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("面窝"),
            difficulty = Difficulty.EASY,
            durationMinutes = 15,
            calories = 350,
            ingredients = listOf(
                Ingredient("米浆", "200g", 1.0),
                Ingredient("黄豆粉", "30g", 0.5),
                Ingredient("葱花", "10g", 0.2),
                Ingredient("芝麻", "5g", 0.3),
            ),
            description = "面窝是武汉过早的经典油炸小吃，中间薄脆如纸、边沿厚实软糯。" +
                "用特制凹勺舀米浆入油锅炸制，成型后中间凹陷处薄脆咔嚓作响。" +
                "配上豆浆或蛋酒，是武汉人最朴实的早晨。",
            article = Article(
                title = "面窝：一勺米浆的油炸艺术",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "凹勺定形状",
                        "面窝的灵魂在于特制的铁制凹勺，米浆倒入后中间自然下陷。" +
                            "入油锅炸制时，中间薄处炸成焦脆薄片，边沿厚处保持软糯，一口两种口感。",
                    ),
                    ArticleSection(
                        "黄豆粉增香",
                        "米浆中加入黄豆粉是老配方，炸出来色泽金黄、豆香浓郁。" +
                            "撒葱花和芝麻增香，刚出锅的面窝最脆最好吃，放凉了就软了。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "面窝制作教学", SAMPLE_VIDEO, foodImage("面窝"), 240),
            ),
            steps = listOf(
                CookingStep(1, "调米浆", "大米磨浆加黄豆粉、盐、葱花搅匀至合适的稠度。", 120, null, null),
                CookingStep(2, "热油", "油锅烧至七成热，凹勺入油预热。", 180, null, null),
                CookingStep(3, "炸制", "米浆倒入凹勺，连勺入油锅炸至金黄定型，脱勺继续炸脆。", 300, null, null),
                CookingStep(4, "沥油出锅", "捞出沥油，撒芝麻即可。", 60, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 3.0,
        ),
        Dish(
            id = "yu_hu_tang_fen",
            name = "鲜鱼糊汤粉",
            tagline = "鲜鱼熬汤勾芡，配油条吸饱汤汁，暖胃香辣",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("鲜鱼糊汤粉"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 40,
            calories = 400,
            ingredients = listOf(
                Ingredient("米粉", "150g", 2.0),
                Ingredient("鲜鱼", "200g", 5.0),
                Ingredient("胡椒粉", "5g", 0.3),
                Ingredient("淀粉", "20g", 0.5),
                Ingredient("油条", "1根", 2.0),
            ),
            description = "鲜鱼糊汤粉是武汉过早的暖胃担当，鲜鱼熬汤勾芡成糊，浓稠胡椒味直冲鼻腔。" +
                "米粉泡在鱼糊汤中吸饱鲜味，再配一根油条蘸汤吃，暖胃又饱腹。" +
                "一碗下肚浑身暖和，是冬日的过早首选。",
            article = Article(
                title = "糊汤粉：鱼鲜与胡椒的碰撞",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "鲫鱼熬汤底",
                        "选用小鲫鱼熬煮数小时至骨肉分离，滤去鱼刺后鱼汤浓白。" +
                            "加大量白胡椒是点睛之笔，辛辣暖胃，去鱼腥的同时激发鲜味。",
                    ),
                    ArticleSection(
                        "油条是绝配",
                        "糊汤粉必须配油条吃，油条撕段蘸入浓稠的鱼糊汤中，吸饱汤汁后外软内脆。" +
                            "这是武汉过早最经典的汤粉配油条组合。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "鲜鱼糊汤粉教学", SAMPLE_VIDEO, foodImage("鲜鱼糊汤粉"), 480),
            ),
            steps = listOf(
                CookingStep(1, "熬鱼汤", "小鲫鱼加姜片熬煮1小时至汤浓白，滤去鱼刺。", 2400, null, null),
                CookingStep(2, "调糊", "鱼汤加白胡椒粉、盐，水淀粉勾芡至浓稠糊状。", 180, null, null),
                CookingStep(3, "煮米粉", "米粉烫熟捞出装入碗中。", 120, null, null),
                CookingStep(4, "浇汤配料", "鱼糊浇在米粉上，撒葱花，配油条上桌。", 60, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 10.0,
        ),
        Dish(
            id = "wuhan_tang_bao",
            name = "武汉汤包",
            tagline = "皮薄馅鲜、汤汁饱满，先开窗后喝汤",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("武汉汤包"),
            difficulty = Difficulty.HARD,
            durationMinutes = 60,
            calories = 380,
            ingredients = listOf(
                Ingredient("面粉", "200g", 1.5),
                Ingredient("猪肉馅", "150g", 5.0),
                Ingredient("皮冻", "50g", 2.0),
                Ingredient("姜末", "10g", 0.2),
                Ingredient("香醋", "15ml", 0.3),
            ),
            description = "武汉汤包皮薄如纸、馅鲜汁满，是过早中的精致担当。" +
                "吃法讲究先开窗、后喝汤，轻轻咬破皮吸出鲜汤，再蘸姜醋吃馅。" +
                "皮冻遇热化汤是汤汁饱满的秘诀，一口一个鲜香四溢。",
            article = Article(
                title = "汤包里的灌汤魔法",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "皮冻化汤",
                        "汤包的汤汁来自猪皮冻，猪皮熬煮后冷藏凝固，切碎拌入肉馅。" +
                            "蒸制时皮冻遇热融化成汤，被封在面皮里，咬破便汤汁四溢。",
                    ),
                    ArticleSection(
                        "皮薄如纸靠擀功",
                        "汤包皮要擀得中间厚、边缘薄如蝉翼。" +
                            "包馅时捏出十八个褶以上，既美观又防止漏汤。蒸制时间精准控制，过则皮破漏汤。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "武汉汤包制作教学", SAMPLE_VIDEO, foodImage("武汉汤包"), 720),
            ),
            steps = listOf(
                CookingStep(1, "制皮冻", "猪皮熬煮烂后冷藏凝固，切碎丁备用。", 1800, null, null),
                CookingStep(2, "调馅", "猪肉馅加皮冻丁、姜末、调料拌匀冷藏。", 300, null, null),
                CookingStep(3, "擀皮包馅", "面皮擀薄，包入馅料捏出褶子。", 600, null, null),
                CookingStep(4, "蒸制", "大火蒸8分钟至皮透亮，配姜醋上桌。", 480, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 18.0,
        ),
        Dish(
            id = "zhong_you_shao_mai",
            name = "重油烧麦",
            tagline = "皮薄馅大、糯米鲜肉双暴击，配大碗茶解腻",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("重油烧麦"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 45,
            calories = 480,
            ingredients = listOf(
                Ingredient("糯米", "150g", 1.5),
                Ingredient("鲜肉", "100g", 4.0),
                Ingredient("香菇", "30g", 1.0),
                Ingredient("笋丁", "30g", 1.0),
                Ingredient("烧麦皮", "20张", 2.0),
                Ingredient("猪油", "20g", 1.0),
            ),
            description = "重油烧麦是武汉过早的重头戏，皮薄馅大、糯米鲜肉双暴击。" +
                "猪油拌糯米饭，裹上鲜肉香菇笋丁馅，蒸出来油润透亮。" +
                "配一碗大碗茶解腻，是老武汉最地道的过早组合。",
            article = Article(
                title = "重油烧麦与过早配茶文化",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "猪油是灵魂",
                        "重油烧麦之所以叫重油，是因为糯米饭要用猪油拌匀，蒸出来颗粒油亮不粘连。" +
                            "馅料中的鲜肉丁要肥瘦相间，蒸制时油脂渗入糯米更香。",
                    ),
                    ArticleSection(
                        "配大碗茶解腻",
                        "老武汉吃重油烧麦必配大碗茶，粗茶苦涩恰好解油烧麦的腻。" +
                            "这种油烧麦加大碗茶的组合是武汉过早文化的独特风景线。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "重油烧麦教学", SAMPLE_VIDEO, foodImage("重油烧麦"), 600),
            ),
            steps = listOf(
                CookingStep(1, "蒸糯米", "糯米浸泡后蒸熟，趁热拌入猪油。", 1200, null, null),
                CookingStep(2, "炒馅料", "鲜肉丁、香菇丁、笋丁炒香调味。", 300, null, null),
                CookingStep(3, "拌馅", "炒好的馅料与糯米饭拌匀。", 120, null, null),
                CookingStep(4, "包烧麦", "烧麦皮包入糯米馅，收口捏松散花形。", 300, null, null),
                CookingStep(5, "蒸制", "大火蒸15分钟至皮透明即可。", 900, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 14.0,
        ),
        Dish(
            id = "dan_jiu",
            name = "蛋酒",
            tagline = "过早的甜蜜收尾，甜而不腻暖胃暖心",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("蛋酒"),
            difficulty = Difficulty.EASY,
            durationMinutes = 10,
            calories = 200,
            ingredients = listOf(
                Ingredient("米酒", "200ml", 3.0),
                Ingredient("鸡蛋", "1个", 1.0),
                Ingredient("白糖", "15g", 0.2),
            ),
            description = "蛋酒是武汉过早的甜蜜收尾，米酒煮开冲入蛋花，甜而不腻暖胃暖心。" +
                "做法极简却味道温润，一碗蛋酒配热干面是武汉人最经典的过早搭配。" +
                "冬天来一碗，从胃暖到全身。",
            article = Article(
                title = "蛋酒：过早的甜蜜句号",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "米酒是基底",
                        "武汉蛋酒用的是本地桂花米酒，甜度自然酒味温和。" +
                            "米酒小火煮开至微沸，打入蛋花形成丝滑蛋絮，甜香扑鼻。",
                    ),
                    ArticleSection(
                        "冲蛋有技巧",
                        "鸡蛋打散后，米酒滚沸时离火快速冲入，边冲边搅。" +
                            "蛋花细如丝絮漂浮在酒汤中，口感嫩滑不结块，这碗蛋酒才算合格。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "蛋酒制作教学", SAMPLE_VIDEO, foodImage("蛋酒"), 180),
            ),
            steps = listOf(
                CookingStep(1, "煮米酒", "米酒倒入锅中小火煮至微沸。", 180, null, null),
                CookingStep(2, "冲蛋花", "鸡蛋打散，米酒离火快速冲入蛋液中搅匀。", 60, null, null),
                CookingStep(3, "调味", "加白糖搅匀，倒碗即可。", 30, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 5.0,
        ),
        Dish(
            id = "you_bing_bao_shao_mai",
            name = "油饼包烧麦",
            tagline = "炸油饼夹烧麦，碳水加碳水的神仙组合",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("油饼包烧麦"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 30,
            calories = 600,
            ingredients = listOf(
                Ingredient("面粉", "150g", 1.0),
                Ingredient("糯米", "100g", 1.0),
                Ingredient("鲜肉", "80g", 3.0),
                Ingredient("香菇", "20g", 0.8),
                Ingredient("黑胡椒", "3g", 0.2),
            ),
            description = "油饼包烧麦是武汉过早的神仙组合，炸得金黄酥脆的油饼对半切开，夹入热腾腾的糯米烧麦。" +
                "碳水加碳水的快乐无人能挡，外脆内糯一口爆汁。" +
                "这是武汉人碳水自由的终极表达。",
            article = Article(
                title = "碳水加碳水的快乐",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "油饼要现炸",
                        "油饼用发面擀薄入油锅炸制，鼓起大泡金黄酥脆。" +
                            "刚出锅的油饼中间空心，对半切开正好可以夹馅，外皮的酥脆是这道小吃的灵魂。",
                    ),
                    ArticleSection(
                        "烧麦做馅",
                        "糯米鲜肉烧麦做馅是武汉人的创意，蒸好的烧麦去掉外皮塞入油饼中。" +
                            "咬下去先脆后糯，再咀嚼鲜肉香菇的咸香，层次丰富到令人惊叹。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "油饼包烧麦教学", SAMPLE_VIDEO, foodImage("油饼包烧麦"), 480),
            ),
            steps = listOf(
                CookingStep(1, "炸油饼", "发面擀薄入油锅炸至金黄鼓泡，捞出对半切开。", 300, null, null),
                CookingStep(2, "蒸烧麦", "糯米鲜肉烧麦蒸15分钟至熟。", 900, null, null),
                CookingStep(3, "组合", "油饼切口夹入烧麦，撒黑胡椒即可。", 60, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 10.0,
        ),
        Dish(
            id = "huan_xi_tuo",
            name = "欢喜坨",
            tagline = "金黄酥脆的炸麻团，外脆内糯",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("欢喜坨"),
            difficulty = Difficulty.EASY,
            durationMinutes = 20,
            calories = 300,
            ingredients = listOf(
                Ingredient("糯米粉", "150g", 1.5),
                Ingredient("芝麻", "30g", 1.0),
                Ingredient("白糖", "30g", 0.3),
                Ingredient("豆沙", "50g", 1.5),
                Ingredient("食用油", "500ml", 2.0),
            ),
            description = "欢喜坨是武汉过早的甜蜜小吃，其实就是炸麻团，外裹白芝麻炸至金黄酥脆。" +
                "咬开脆壳，内里糯米团软糯拉丝，有的还包着豆沙馅。" +
                "名字喜庆味道甜蜜，是老武汉人的童年回忆。",
            article = Article(
                title = "欢喜坨：一个喜庆的名字",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "名字的由来",
                        "欢喜坨是武汉方言，意思是欢喜的圆球。" +
                            "逢年过节或家有喜事时常做这道小吃，金黄圆润寓意团圆欢喜，是武汉人最喜庆的早点之一。",
                    ),
                    ArticleSection(
                        "芝麻要裹满",
                        "糯米团搓圆后要在水里滚一下再裹芝麻，这样芝麻炸制时不会脱落。" +
                            "炸的时候要不停翻动让受热均匀，外壳才能金黄酥脆不焦糊。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "欢喜坨制作教学", SAMPLE_VIDEO, foodImage("欢喜坨"), 300),
            ),
            steps = listOf(
                CookingStep(1, "和面", "糯米粉加白糖温水和成团，包入豆沙馅搓圆。", 180, null, null),
                CookingStep(2, "裹芝麻", "糯米团沾水后滚满白芝麻，压实。", 120, null, null),
                CookingStep(3, "炸制", "油锅五成热下锅，小火慢炸至金黄膨胀，不停翻动。", 480, null, null),
                CookingStep(4, "沥油出锅", "捞出沥油，稍凉即可食用。", 60, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 3.0,
        ),
        Dish(
            id = "niu_rou_fen",
            name = "牛肉粉",
            tagline = "浓汤牛肉米粉，武汉人过早的硬核选择",
            cuisine = "武汉早餐",
            coverImageUrl = foodImage("牛肉粉"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 40,
            calories = 500,
            ingredients = listOf(
                Ingredient("米粉", "200g", 2.5),
                Ingredient("牛肉", "150g", 8.0),
                Ingredient("牛骨", "300g", 5.0),
                Ingredient("香料包", "1包", 1.0),
                Ingredient("葱花", "10g", 0.2),
                Ingredient("辣椒油", "10ml", 0.5),
            ),
            description = "牛肉粉是武汉人过早的硬核选择，牛骨熬汤浓白鲜香，大片牛肉铺满米粉上。" +
                "浇一勺辣椒油红亮诱人，嗦一口米粉吸饱汤汁鲜辣过瘾。" +
                "比热干面更扎实，是重口味爱好者的过早首选。",
            article = Article(
                title = "牛肉粉：一碗过早的硬核担当",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "牛骨汤是底气",
                        "好的牛肉粉汤底要用牛骨熬制数小时，加香料包去膻提鲜。" +
                            "汤色浓白味道醇厚，牛肉在汤中炖至酥烂入味，切片铺在粉上分量十足。",
                    ),
                    ArticleSection(
                        "辣椒油点睛",
                        "牛肉粉的灵魂调味是那一勺辣椒油，用牛油泼制最香。" +
                            "红亮的辣油浮在汤面，嗦粉时沾上一点，鲜辣过瘾，武汉人的早晨就此唤醒。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "牛肉粉完整教学", SAMPLE_VIDEO, foodImage("牛肉粉"), 600),
            ),
            steps = listOf(
                CookingStep(1, "熬牛骨汤", "牛骨加香料包熬煮2小时至汤浓白，放入牛肉炖至酥烂。", 4800, null, null),
                CookingStep(2, "切牛肉", "牛肉捞出切薄片备用。", 120, null, null),
                CookingStep(3, "煮粉", "米粉烫熟捞出装入碗中。", 120, null, null),
                CookingStep(4, "组装", "米粉上铺牛肉片，浇牛骨汤，加辣椒油、葱花。", 60, null, null),
            ),
            mealType = MealType.BREAKFAST,
            estimatedCostYuan = 14.0,
        ),

        // ======================== WUHAN LUNCH DISHES (mealType = LUNCH) ========================

        Dish(
            id = "ezhou_steam_fish",
            name = "清蒸武昌鱼(鄂菜版)",
            tagline = "鄂菜头牌，嫩如豆腐，葱姜热油一浇激发鲜香",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("清蒸武昌鱼鄂菜版"),
            difficulty = Difficulty.EASY,
            durationMinutes = 20,
            calories = 250,
            ingredients = listOf(
                Ingredient("武昌鱼", "1条", 35.0),
                Ingredient("姜丝", "20g", 0.5),
                Ingredient("葱丝", "20g", 0.5),
                Ingredient("蒸鱼豉油", "30ml", 2.0),
                Ingredient("食用油", "30ml", 1.0),
            ),
            description = "清蒸武昌鱼是鄂菜头牌，选用正宗武昌鱼清蒸至嫩如豆腐。" +
                "出锅后铺葱姜浇滚油激香，再淋蒸鱼豉油，原汁原味鲜美至极。" +
                "才饮长沙水又食武昌鱼让这道菜名扬天下。",
            article = Article(
                title = "武昌鱼与毛主席的诗",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "才食武昌鱼",
                        "毛主席的水调歌头游泳中才饮长沙水又食武昌鱼让武昌鱼名扬四海。" +
                            "正宗武昌鱼指梁子湖产的团头鲂，肉质细嫩刺少，清蒸最能体现本味。",
                    ),
                    ArticleSection(
                        "葱油激香",
                        "蒸好的鱼铺上大量葱丝姜丝，浇上滚烫热油，刺啦一声激出葱姜香气。" +
                            "最后淋蒸鱼豉油调味，不放一滴多余调料，鱼肉鲜甜原汁原味。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "清蒸武昌鱼鄂菜版教学", SAMPLE_VIDEO, foodImage("清蒸武昌鱼鄂菜版"), 360),
            ),
            steps = listOf(
                CookingStep(1, "处理鱼", "武昌鱼去鳞内脏洗净，两面划刀，抹料酒姜片去腥。", 300, null, null),
                CookingStep(2, "上锅蒸", "水烧上汽，鱼盘入锅大火蒸8分钟至刚断生。", 480, null, null),
                CookingStep(3, "铺葱丝", "倒掉蒸汁，铺大量葱丝姜丝。", 60, null, null),
                CookingStep(4, "浇油淋汁", "滚烫热油浇在葱姜丝上激香，淋蒸鱼豉油即可。", 60, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 55.0,
        ),
        Dish(
            id = "pai_gu_ou_tang",
            name = "排骨藕汤",
            tagline = "湖北人的亲情牌，洪湖粉藕慢煨拉丝",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("排骨藕汤"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 90,
            calories = 380,
            ingredients = listOf(
                Ingredient("洪湖粉藕", "500g", 12.0),
                Ingredient("猪肋排", "400g", 20.0),
                Ingredient("姜片", "15g", 0.3),
                Ingredient("盐", "5g", 0.1),
                Ingredient("葱花", "10g", 0.2),
            ),
            description = "排骨藕汤是湖北人的亲情牌家常汤，洪湖粉藕慢煨至拉丝，排骨酥烂汤汁粉甜。" +
                "一锅汤煨上两三小时，藕断丝连是最暖心的画面。" +
                "这是湖北人逢年过节餐桌上必有的一道汤。",
            article = Article(
                title = "排骨藕汤：湖北人的乡愁",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "洪湖粉藕最正宗",
                        "排骨藕汤必须用洪湖粉藕，淀粉含量高、炖煮后粉糯拉丝。" +
                            "脆藕炖不出这种绵密口感，粉藕才是这锅汤的灵魂。选藕时看藕节短粗、表皮微锈的最好。",
                    ),
                    ArticleSection(
                        "慢煨出真味",
                        "排骨先焯水去血沫，与藕块加足量水大火烧开后转小火慢煨两小时以上。" +
                            "汤色微粉、藕块酥烂拉丝，只加盐调味不加香料，喝的是食材本味。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "排骨藕汤教学", SAMPLE_VIDEO, foodImage("排骨藕汤"), 1200),
            ),
            steps = listOf(
                CookingStep(1, "焯排骨", "排骨冷水下锅煮开撇沫，捞出洗净。", 300, null, null),
                CookingStep(2, "切藕", "粉藕去皮切大块。", 120, null, null),
                CookingStep(3, "慢煨", "排骨、藕块、姜片加足量水大火烧开，转小火煨2小时。", 4800, null, null),
                CookingStep(4, "调味", "加盐调味，撒葱花即可。", 60, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 45.0,
        ),
        Dish(
            id = "mian_yang_san_zheng",
            name = "沔阳三蒸",
            tagline = "无蒸不成席，粉蒸鱼、鱼糕、青菜三样同蒸",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("沔阳三蒸"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 60,
            calories = 420,
            ingredients = listOf(
                Ingredient("草鱼", "300g", 12.0),
                Ingredient("鱼糕", "200g", 10.0),
                Ingredient("南瓜", "200g", 3.0),
                Ingredient("米粉", "100g", 2.0),
                Ingredient("姜蒜", "20g", 0.5),
            ),
            description = "沔阳三蒸是湖北仙桃沔阳的传统名菜，无蒸不成席说的就是它。" +
                "粉蒸鱼、鱼糕、青菜三样同蒸上桌，蒸菜原汁原味清淡鲜美。" +
                "一笼三味各具特色，是鄂菜蒸功夫的集大成者。",
            article = Article(
                title = "无蒸不成席的沔阳三蒸",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "三蒸的由来",
                        "沔阳三蒸源自江汉平原的蒸菜传统，三蒸指粉蒸鱼、蒸鱼糕和蒸青菜。" +
                            "相传陈友谅起义时百姓蒸菜犒军，后演变为宴席必备，有无蒸不成席的说法。",
                    ),
                    ArticleSection(
                        "米粉是关键",
                        "粉蒸鱼要用自制米粉，大米加花椒八角炒至微黄后磨成粗颗粒。" +
                            "米粉裹住食材蒸制，既吸油脂又增米香，蒸出来的菜干爽不水塌，米香与鲜味交融。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "沔阳三蒸教学", SAMPLE_VIDEO, foodImage("沔阳三蒸"), 900),
            ),
            steps = listOf(
                CookingStep(1, "制米粉", "大米加花椒八角炒微黄，打碎成粗颗粒。", 300, null, null),
                CookingStep(2, "裹粉", "草鱼块、鱼糕、南瓜块分别裹满米粉。", 180, null, null),
                CookingStep(3, "码笼同蒸", "三种食材码入蒸笼，大火蒸30分钟。", 1800, null, null),
                CookingStep(4, "出笼", "撒葱花，蘸料上桌。", 60, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 50.0,
        ),
        Dish(
            id = "hong_cai_tai_la_rou",
            name = "红菜薹炒腊肉",
            tagline = "清朝金殿玉菜，洪山菜薹配烟熏腊肉",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("红菜薹炒腊肉"),
            difficulty = Difficulty.EASY,
            durationMinutes = 15,
            calories = 280,
            ingredients = listOf(
                Ingredient("洪山红菜薹", "400g", 10.0),
                Ingredient("腊肉", "100g", 12.0),
                Ingredient("姜蒜", "10g", 0.5),
                Ingredient("干辣椒", "5g", 0.3),
            ),
            description = "红菜薹炒腊肉是鄂菜经典，洪山菜薹脆嫩清甜，配烟熏腊肉咸香下饭。" +
                "菜薹吸收腊肉的油脂更香润，腊肉被菜薹的清甜解腻。" +
                "这道菜上过清朝御膳桌，是金殿玉菜的传说。",
            article = Article(
                title = "洪山菜薹：金殿玉菜",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "洪山宝塔下的菜薹",
                        "正宗红菜薹产自武汉洪山宝通寺塔影所及之处，据说塔影下的菜薹最鲜嫩。" +
                            "清朝时曾作为贡品进献宫廷，被称为金殿玉菜，是湖北独有的冬季时令蔬菜。",
                    ),
                    ArticleSection(
                        "腊肉是绝配",
                        "红菜薹配烟熏腊肉是天生一对，腊肉煸出油脂炒菜薹，菜薹吸饱腊味更香。" +
                            "菜薹梗要掐段去皮，只取嫩心炒制，大火快炒断生即出锅，保持脆嫩。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "红菜薹炒腊肉教学", SAMPLE_VIDEO, foodImage("红菜薹炒腊肉"), 240),
            ),
            steps = listOf(
                CookingStep(1, "处理菜薹", "红菜薹掐段去皮，取嫩心洗净沥干。", 120, null, null),
                CookingStep(2, "煸腊肉", "腊肉切片小火煸出油，下姜蒜干辣椒爆香。", 120, null, null),
                CookingStep(3, "炒菜薹", "大火下菜薹快速翻炒至断生。", 90, null, null),
                CookingStep(4, "调味出锅", "加少许盐调味，翻匀出锅。", 30, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 25.0,
        ),
        Dish(
            id = "huang_zhou_dong_po_rou",
            name = "黄州东坡肉",
            tagline = "苏东坡谪居黄州所创，咸香口、肥而不腻",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("黄州东坡肉"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 90,
            calories = 550,
            ingredients = listOf(
                Ingredient("五花肉", "500g", 18.0),
                Ingredient("酱油", "50ml", 2.0),
                Ingredient("料酒", "30ml", 1.0),
                Ingredient("冰糖", "30g", 1.0),
                Ingredient("八角", "3g", 0.3),
                Ingredient("姜", "20g", 0.3),
            ),
            description = "黄州东坡肉是苏东坡谪居黄州时所创，大块五花肉慢炖至红亮酥烂。" +
                "与杭州东坡肉不同，黄州东坡肉偏咸香口，肥而不腻入口即化。" +
                "一块肉一碗饭，是文人美食的最佳注脚。",
            article = Article(
                title = "苏东坡的黄州味道",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "谪居黄州创名菜",
                        "苏轼被贬黄州今湖北黄冈时，当地猪肉便宜，他便潜心研究烹法。" +
                            "慢火少水多酒，炖出红亮酥烂的东坡肉，还写下了猪肉颂记录做法，文人美食由此传世。",
                    ),
                    ArticleSection(
                        "咸香有别于杭州",
                        "黄州东坡肉与杭州版不同，调味偏咸香不甜腻。" +
                            "用酱油和料酒为主，冰糖用量少，更突出肉香和酱香。炖至肥肉入口即化、瘦肉酥而不柴，才是正宗。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "黄州东坡肉教学", SAMPLE_VIDEO, foodImage("黄州东坡肉"), 1200),
            ),
            steps = listOf(
                CookingStep(1, "焯水切块", "五花肉整块焯水后切大方块。", 300, null, null),
                CookingStep(2, "煎上色", "肉块皮朝下煎至金黄，逼出多余油脂。", 180, null, null),
                CookingStep(3, "炖煮", "锅底铺姜片，肉块皮朝上码入，加酱油、料酒、冰糖、八角，小火炖90分钟。", 5400, null, null),
                CookingStep(4, "收汁", "大火收汁至浓稠，翻匀装盘。", 300, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 35.0,
        ),
        Dish(
            id = "pi_tiao_shan_yu",
            name = "皮条鳝鱼",
            tagline = "荆州两百年功夫菜，外酥里嫩浇糖醋汁",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("皮条鳝鱼"),
            difficulty = Difficulty.HARD,
            durationMinutes = 45,
            calories = 400,
            ingredients = listOf(
                Ingredient("鳝鱼", "400g", 25.0),
                Ingredient("淀粉", "50g", 1.0),
                Ingredient("糖醋汁", "100ml", 3.0),
                Ingredient("面粉", "30g", 0.5),
                Ingredient("食用油", "500ml", 3.0),
            ),
            description = "皮条鳝鱼是荆州两百年功夫菜，鳝鱼切段裹粉油炸至外酥里嫩，浇糖醋汁亮红诱人。" +
                "形如皮条而得名，一口咬下酥壳咔嚓作响，内里鳝肉嫩滑鲜甜。" +
                "是鄂菜刀工与火候的双重考验。",
            article = Article(
                title = "皮条鳝鱼的两百年传承",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "功夫在刀工",
                        "皮条鳝鱼讲究刀工，鳝鱼去骨切段后要剞花刀，既美观又易入味。" +
                            "炸制时花刀展开形如皮条，因而得名。这道菜始创于清道光年间的荆州沙市，至今近两百年历史。",
                    ),
                    ArticleSection(
                        "两炸两浇汁",
                        "鳝鱼要经过两次油炸，第一次定型、第二次炸脆。" +
                            "糖醋汁另锅熬至浓稠，浇在炸好的鳝鱼上，滋啦作响挂满酱汁。外酥里嫩、酸甜适口，是宴席上的镇桌大菜。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "皮条鳝鱼教学", SAMPLE_VIDEO, foodImage("皮条鳝鱼"), 600),
            ),
            steps = listOf(
                CookingStep(1, "处理鳝鱼", "鳝鱼去骨切段，剞花刀，裹淀粉。", 300, null, null),
                CookingStep(2, "第一次炸", "油锅六成热下鳝鱼炸至定型捞出。", 240, null, null),
                CookingStep(3, "第二次炸", "油温升至八成热复炸至金黄酥脆。", 180, null, null),
                CookingStep(4, "浇汁", "糖醋汁熬浓浇在鳝鱼上，翻匀装盘。", 120, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 48.0,
        ),
        Dish(
            id = "zhong_xiang_pan_long_cai",
            name = "钟祥盘龙菜",
            tagline = "嘉靖皇帝御膳、湖北非遗，吃肉不见肉",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("钟祥盘龙菜"),
            difficulty = Difficulty.HARD,
            durationMinutes = 90,
            calories = 450,
            ingredients = listOf(
                Ingredient("瘦肉", "300g", 15.0),
                Ingredient("鲜鱼", "200g", 10.0),
                Ingredient("淀粉", "50g", 1.0),
                Ingredient("蛋清", "3个", 3.0),
                Ingredient("鸡蛋皮", "2张", 2.0),
            ),
            description = "钟祥盘龙菜是湖北非遗名菜，嘉靖皇帝御膳吃肉不见肉的传奇。" +
                "瘦肉鱼茸裹蛋皮卷成圆筒蒸制，切片后形如盘龙盘旋于盘中。" +
                "口感鲜嫩弹滑，是钟祥人婚宴酒席上的头牌大菜。",
            article = Article(
                title = "嘉靖皇帝的吃肉不见肉",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "皇帝御膳的传说",
                        "相传明嘉靖皇帝朱厚熜进京继位前，钟祥厨师将肉鱼剁茸用蛋皮包裹蒸熟，做成吃肉不见肉的菜肴便于路途食用。" +
                            "后成为宫廷御膳，赐名盘龙菜，是湖北省级非物质文化遗产。",
                    ),
                    ArticleSection(
                        "卷蒸切的艺术",
                        "盘龙菜做法讲究：瘦肉和鲜鱼剁成茸，加淀粉蛋清搅打上劲。" +
                            "摊蛋皮铺上肉茸卷成圆筒，大火蒸熟后切片摆盘，切面螺旋如龙盘旋。入口弹嫩鲜美，肉香与鱼鲜交融。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "钟祥盘龙菜教学", SAMPLE_VIDEO, foodImage("钟祥盘龙菜"), 900),
            ),
            steps = listOf(
                CookingStep(1, "制茸", "瘦肉和鲜鱼分别剁茸，加淀粉、蛋清搅打上劲。", 600, null, null),
                CookingStep(2, "摊蛋皮", "鸡蛋摊成薄蛋皮备用。", 180, null, null),
                CookingStep(3, "卷制", "蛋皮铺上肉茸卷成紧实圆筒。", 300, null, null),
                CookingStep(4, "蒸制", "大火蒸30分钟至熟透。", 1800, null, null),
                CookingStep(5, "切片摆盘", "蒸好后晾凉切片，盘旋摆盘即可。", 120, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 55.0,
        ),
        Dish(
            id = "yuan_tang_cuan_yu_wan",
            name = "原汤汆鱼丸",
            tagline = "一鱼两吃，鱼头熬汤、鱼身做丸，原汁本鲜",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("原汤汆鱼丸"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 50,
            calories = 300,
            ingredients = listOf(
                Ingredient("草鱼", "1条", 20.0),
                Ingredient("淀粉", "30g", 0.5),
                Ingredient("蛋清", "2个", 2.0),
                Ingredient("葱花", "10g", 0.2),
                Ingredient("姜末", "10g", 0.2),
            ),
            description = "原汤汆鱼丸是鄂菜一鱼两吃的代表，鱼头熬汤、鱼身做丸，原汁本鲜。" +
                "鱼丸手工打至弹嫩，在鱼头汤中汆熟，汤白丸嫩鲜美至极。" +
                "不加任何多余调料，吃的就是鱼的本味鲜甜。",
            article = Article(
                title = "一鱼两吃的智慧",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "鱼头熬汤底",
                        "选用草鱼，鱼头鱼骨煎后熬煮成浓白汤底。" +
                            "这是原汤的来源，汤色奶白味道醇厚，鱼丸在其中汆煮，汤汁渗入丸中，鲜上加鲜。",
                    ),
                    ArticleSection(
                        "手打鱼丸弹嫩",
                        "鱼身取肉刮蓉，加蛋清淀粉顺方向搅打上劲。" +
                            "挤丸入冷水定型，再入鱼汤小火汆熟。好的鱼丸洁白弹牙，入口即化，是刀工与手感的双重考验。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "原汤汆鱼丸教学", SAMPLE_VIDEO, foodImage("原汤汆鱼丸"), 720),
            ),
            steps = listOf(
                CookingStep(1, "取鱼肉", "草鱼去骨取肉，刮成鱼蓉。", 600, null, null),
                CookingStep(2, "打鱼丸", "鱼蓉加蛋清、淀粉、盐搅打上劲，挤丸入冷水。", 600, null, null),
                CookingStep(3, "熬鱼汤", "鱼头鱼骨煎黄加水熬煮成浓白汤。", 1800, null, null),
                CookingStep(4, "汆鱼丸", "鱼汤烧开下鱼丸小火汆熟，调味撒葱花。", 300, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 40.0,
        ),
        Dish(
            id = "la_wei_he_zheng",
            name = "腊味合蒸",
            tagline = "荆楚腊味一锅蒸，咸香下饭",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("腊味合蒸"),
            difficulty = Difficulty.EASY,
            durationMinutes = 40,
            calories = 480,
            ingredients = listOf(
                Ingredient("腊肉", "200g", 15.0),
                Ingredient("腊鱼", "200g", 12.0),
                Ingredient("腊肠", "100g", 8.0),
                Ingredient("干豆角", "100g", 3.0),
                Ingredient("姜蒜", "15g", 0.3),
            ),
            description = "腊味合蒸是荆楚腊味经典，腊肉、腊鱼、腊肠一锅同蒸，咸香浓郁下饭至极。" +
                "干豆角垫底吸收腊味油脂，蒸出来香气四溢。" +
                "这是湖北人冬天的年味记忆，一上桌满屋都是腊香。",
            article = Article(
                title = "腊味合蒸：冬日的年味",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "荆楚腊味传统",
                        "湖北人入冬后便开始腌腊味，腊肉、腊鱼、腊肠挂满屋檐是过年的标志。" +
                            "腊味合蒸将三种腊味拼在一起蒸，是湖北人过年宴席上的必备菜，满屋腊香就是年味。",
                    ),
                    ArticleSection(
                        "干豆角垫底",
                        "腊味合蒸的妙处在于干豆角垫底，干豆角吸收腊味蒸出的油脂，比腊味本身还好吃。" +
                            "腊味先泡水去咸再蒸，蒸制时腊油滴入干豆角中，下饭一绝。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "腊味合蒸教学", SAMPLE_VIDEO, foodImage("腊味合蒸"), 600),
            ),
            steps = listOf(
                CookingStep(1, "泡腊味", "腊肉、腊鱼、腊肠温水泡软去咸，切片。", 600, null, null),
                CookingStep(2, "泡干豆角", "干豆角温水泡发，铺碗底。", 600, null, null),
                CookingStep(3, "码碗", "腊味片码在干豆角上，铺姜蒜。", 120, null, null),
                CookingStep(4, "蒸制", "大火蒸40分钟至腊味酥软，倒扣出盘。", 2400, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 38.0,
        ),
        Dish(
            id = "kui_hua_dou_fu",
            name = "葵花豆腐",
            tagline = "武汉十大名菜之一，造型如葵花，清淡鲜嫩",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("葵花豆腐"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 30,
            calories = 220,
            ingredients = listOf(
                Ingredient("豆腐", "300g", 3.0),
                Ingredient("鸡蛋", "3个", 3.0),
                Ingredient("火腿", "50g", 5.0),
                Ingredient("高汤", "200ml", 2.0),
                Ingredient("淀粉", "20g", 0.5),
            ),
            description = "葵花豆腐是武汉十大名菜之一，豆腐塑形如葵花绽放，造型精美清淡鲜嫩。" +
                "蛋黄做花心、蛋白做花瓣，蒸后浇高汤玻璃芡，晶莹剔透如向日葵。" +
                "是鄂菜素菜荤做、粗菜细做的代表。",
            article = Article(
                title = "葵花豆腐：盘子里的向日葵",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "造型如葵花",
                        "葵花豆腐是武汉老牌名菜，将豆腐泥塑成葵花形状，蛋黄居中做花心，周围蛋白塑成花瓣。" +
                            "蒸制后浇上透明高汤芡汁，整道菜金白相间如一朵盛开的向日葵，赏心悦目。",
                    ),
                    ArticleSection(
                        "清淡鲜嫩的本味",
                        "葵花豆腐不走重油重辣路线，讲究清淡鲜美。" +
                            "豆腐要过筛去豆腥，加蛋清蒸至嫩如凝脂。高汤要用鸡骨火腿吊鲜，勾芡薄而透亮，吃的是豆腐的嫩和高汤的鲜。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "葵花豆腐教学", SAMPLE_VIDEO, foodImage("葵花豆腐"), 480),
            ),
            steps = listOf(
                CookingStep(1, "制豆腐泥", "豆腐过筛成细泥，加蛋清、盐拌匀。", 300, null, null),
                CookingStep(2, "塑形", "豆腐泥铺入盘中塑成葵花形，蛋黄居中，蛋白围边做花瓣。", 300, null, null),
                CookingStep(3, "蒸制", "大火蒸10分钟至豆腐凝固。", 600, null, null),
                CookingStep(4, "浇芡", "高汤烧开勾薄芡，浇在豆腐上即可。", 120, null, null),
            ),
            mealType = MealType.LUNCH,
            estimatedCostYuan = 35.0,
        ),

        // ======================== WUHAN DINNER DISHES (mealType = DINNER) ========================

        Dish(
            id = "lu_ya_bo",
            name = "卤鸭脖",
            tagline = "武汉夜宵卤味王者，麻辣鲜香越啃越上瘾",
            cuisine = "武汉夜宵",
            coverImageUrl = foodImage("卤鸭脖"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 60,
            calories = 280,
            ingredients = listOf(
                Ingredient("鸭脖", "500g", 12.0),
                Ingredient("花椒", "20g", 1.0),
                Ingredient("干辣椒", "30g", 1.0),
                Ingredient("八角", "5g", 0.3),
                Ingredient("桂皮", "5g", 0.3),
                Ingredient("香叶", "3g", 0.2),
                Ingredient("生抽", "50ml", 1.0),
            ),
            description = "卤鸭脖是武汉夜宵的卤味王者，麻辣鲜香越啃越上瘾。" +
                "鸭脖在卤水中浸泡数小时入味至骨，肉质紧实有嚼劲。" +
                "一边啃鸭脖一边喝啤酒，是武汉人夏夜消暑的标准打开方式。",
            article = Article(
                title = "鸭脖：武汉夜宵的C位",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "从街头小吃到国民零食",
                        "卤鸭脖起源于武汉汉口，最初是街头卤味摊的下酒菜。" +
                            "凭借麻辣鲜香、越啃越上头的特质，迅速风靡全国。周黑鸭、精武鸭脖等品牌都从武汉走向全国，成为城市名片。",
                    ),
                    ArticleSection(
                        "卤水是灵魂",
                        "好的卤鸭脖关键在老卤水，二十多种香料慢熬出复合卤香。" +
                            "鸭脖先焯水去血沫，再入卤水小火浸卤1小时以上，关火浸泡入味。麻辣鲜香渗入骨髓，冷吃最过瘾。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "卤鸭脖教学", SAMPLE_VIDEO, foodImage("卤鸭脖"), 720),
            ),
            steps = listOf(
                CookingStep(1, "焯水", "鸭脖冷水下锅煮开撇沫，捞出洗净。", 300, null, null),
                CookingStep(2, "熬卤水", "香料包加水、生抽、冰糖熬煮30分钟出味。", 1800, null, null),
                CookingStep(3, "卤制", "鸭脖入卤水小火煮40分钟，关火浸泡1小时入味。", 6000, null, null),
                CookingStep(4, "捞出", "捞出晾凉，切段即可食用。", 60, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 15.0,
        ),
        Dish(
            id = "chou_gui_yu",
            name = "臭鳜鱼",
            tagline = "闻臭吃香的发酵名鱼，蒜瓣肉鲜嫩入味",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("臭鳜鱼"),
            difficulty = Difficulty.HARD,
            durationMinutes = 50,
            calories = 350,
            ingredients = listOf(
                Ingredient("鳜鱼", "1条", 60.0),
                Ingredient("盐", "50g", 0.5),
                Ingredient("蒜", "50g", 1.0),
                Ingredient("辣椒", "20g", 0.5),
                Ingredient("葱姜", "30g", 0.5),
                Ingredient("料酒", "30ml", 1.0),
            ),
            description = "臭鳜鱼是闻臭吃香的发酵名鱼，鳜鱼腌制发酵后产生独特臭香，烹饪后却鲜嫩入味。" +
                "蒜瓣肉一夹一块，咸鲜微臭回味无穷。" +
                "这道菜虽源于徽菜，在湖北也大受欢迎，是夜宴上的重头戏。",
            article = Article(
                title = "闻臭吃香的奥义",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "发酵出奇鲜",
                        "臭鳜鱼的臭来自腌制发酵，鲜鳜鱼抹盐后压重物发酵6-7天，蛋白质分解产生氨基酸带来浓郁鲜味。" +
                            "闻着臭吃着香，如同榴莲和臭豆腐，爱者欲罢不能。",
                    ),
                    ArticleSection(
                        "蒜瓣肉最迷人",
                        "发酵后的鳜鱼肉质变为蒜瓣状，一夹一整块不散。" +
                            "红烧时加蒜、辣椒、葱姜同烧，臭香与酱香交融，鱼肉入味至骨。吃时先闻臭再品鲜，反差令人上瘾。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "臭鳜鱼教学", SAMPLE_VIDEO, foodImage("臭鳜鱼"), 600),
            ),
            steps = listOf(
                CookingStep(1, "发酵", "鳜鱼抹盐压重物发酵6天至有臭香。", 36000, null, null),
                CookingStep(2, "清洗", "发酵好的鱼洗净去盐，两面划刀。", 120, null, null),
                CookingStep(3, "红烧", "油锅爆香蒜、辣椒、葱姜，下鱼煎黄，加料酒酱油烧15分钟。", 900, null, null),
                CookingStep(4, "收汁出锅", "大火收汁，装盘撒葱花。", 120, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 95.0,
        ),
        Dish(
            id = "jing_sha_jia_yu",
            name = "荆沙甲鱼",
            tagline = "老饕最爱，裙边Q弹满胶原蛋白，酱香滋补",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("荆沙甲鱼"),
            difficulty = Difficulty.HARD,
            durationMinutes = 70,
            calories = 320,
            ingredients = listOf(
                Ingredient("甲鱼", "1只", 80.0),
                Ingredient("荆沙酱", "50g", 5.0),
                Ingredient("黄豆", "30g", 1.0),
                Ingredient("辣椒", "15g", 0.5),
                Ingredient("香料", "10g", 0.5),
                Ingredient("姜", "20g", 0.3),
            ),
            description = "荆沙甲鱼是老饕最爱的高端鄂菜，裙边Q弹满是胶原蛋白，荆沙酱酱香浓郁。" +
                "甲鱼慢炖至裙边软糯入口即化，汤汁浓稠胶质感十足。" +
                "一碗甲鱼一碗饭，滋补又满足，是湖北宴席的压轴硬菜。",
            article = Article(
                title = "甲鱼裙边的胶原蛋白",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "裙边是精华",
                        "甲鱼最珍贵的部位是裙边，甲壳边缘的软肉，富含胶原蛋白。" +
                            "炖煮后裙边变得Q弹软糯，入口即化又带着嚼劲。老饕吃甲鱼只吃裙边，一碗甲鱼半碗胶，滋补养颜。",
                    ),
                    ArticleSection(
                        "荆沙酱定味",
                        "荆沙甲鱼的灵魂调味是荆沙酱，这是荆州特产的豆瓣酱，酱香浓郁微辣。" +
                            "甲鱼用荆沙酱烧制，汤汁浓稠挂满裙边，黄豆增香、辣椒提味，是荆州人待客的最高规格。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "荆沙甲鱼教学", SAMPLE_VIDEO, foodImage("荆沙甲鱼"), 840),
            ),
            steps = listOf(
                CookingStep(1, "处理甲鱼", "甲鱼宰杀去内脏，焯水去膜，剁块。", 600, null, null),
                CookingStep(2, "炒酱", "油锅下荆沙酱、姜、辣椒炒出红油香。", 180, null, null),
                CookingStep(3, "炖煮", "甲鱼入锅翻炒，加黄豆、水小火炖50分钟至裙边软糯。", 3000, null, null),
                CookingStep(4, "收汁", "大火收汁浓稠，装盘即可。", 180, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 120.0,
        ),
        Dish(
            id = "you_men_xiao_long_xia",
            name = "油焖小龙虾",
            tagline = "武汉宵夜霸主，虾肉Q弹入味",
            cuisine = "武汉夜宵",
            coverImageUrl = foodImage("油焖小龙虾"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 40,
            calories = 400,
            ingredients = listOf(
                Ingredient("小龙虾", "1000g", 60.0),
                Ingredient("啤酒", "330ml", 5.0),
                Ingredient("香料", "20g", 1.0),
                Ingredient("辣椒", "30g", 1.0),
                Ingredient("蒜", "50g", 1.0),
                Ingredient("姜", "20g", 0.3),
            ),
            description = "油焖小龙虾是武汉宵夜的霸主，虾肉Q弹入味，麻辣鲜香。" +
                "大量香料和啤酒焖煮，虾壳裹满浓郁酱汁，吮指都舍不得擦。" +
                "夏天武汉的街头巷尾，一盆小龙虾配几瓶啤酒，就是最热闹的夜。",
            article = Article(
                title = "小龙虾：武汉的夏夜符号",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "从外来物种到宵夜之王",
                        "小龙虾原产美洲，上世纪引入中国后在湖北泛滥。" +
                            "武汉人用油焖的做法将其变成宵夜霸主，每到夏天，虾店排队、街边摆桌，整座城市弥漫着麻辣虾香，小龙虾成了武汉夏夜的符号。",
                    ),
                    ArticleSection(
                        "啤酒焖出灵魂",
                        "油焖小龙虾的关键是用啤酒代替水焖煮，啤酒去腥增香，虾肉更Q弹。" +
                            "大量香料和蒜瓣是底味，焖煮时间精准控制在15分钟，虾肉刚好入味不老。出锅后汤汁浓稠，吮壳都是享受。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "油焖小龙虾教学", SAMPLE_VIDEO, foodImage("油焖小龙虾"), 600),
            ),
            steps = listOf(
                CookingStep(1, "处理龙虾", "小龙虾刷洗干净，去虾线。", 600, null, null),
                CookingStep(2, "炒香料", "油锅下大量香料、蒜、姜、辣椒爆香。", 180, null, null),
                CookingStep(3, "焖煮", "龙虾入锅翻炒变红，倒入啤酒焖煮15分钟。", 900, null, null),
                CookingStep(4, "收汁", "大火收汁至浓稠，撒葱花出锅。", 180, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 110.0,
        ),
        Dish(
            id = "xie_jiao_re_gan_mian",
            name = "蟹脚热干面",
            tagline = "蟹脚拌热干面，宵夜创新神作",
            cuisine = "武汉夜宵",
            coverImageUrl = foodImage("蟹脚热干面"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 25,
            calories = 480,
            ingredients = listOf(
                Ingredient("蟹脚", "300g", 18.0),
                Ingredient("热干面", "200g", 3.0),
                Ingredient("辣椒", "15g", 0.5),
                Ingredient("香料", "10g", 0.5),
                Ingredient("葱花", "10g", 0.2),
            ),
            description = "蟹脚热干面是武汉宵夜的创新神作，热干面拌上炒蟹脚的酱汁，鲜辣浓香。" +
                "蟹脚的鲜味渗入芝麻酱中，面条裹满酱汁比蟹脚还抢手。" +
                "是传统热干面的宵夜升级版，一碗惊艳。",
            article = Article(
                title = "宵夜摊上的创新之作",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "热干面的宵夜变身",
                        "蟹脚热干面是近年武汉宵夜摊的创新之作，将过早的热干面搬到了夜宵桌上。" +
                            "蟹脚用香料辣酱爆炒出鲜味，浇在热干面上拌匀，面条吸饱蟹鲜和辣酱，比传统热干面更浓烈过瘾。",
                    ),
                    ArticleSection(
                        "蟹脚炒制是关键",
                        "蟹脚要先过油炸裂壳，再用香料、辣椒、豆瓣酱爆炒。" +
                            "加少许啤酒焖至入味，汤汁浓稠时浇在煮好的热干面上。拌面时蟹鲜、酱香、芝麻香三味交融，一口上头。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "蟹脚热干面教学", SAMPLE_VIDEO, foodImage("蟹脚热干面"), 420),
            ),
            steps = listOf(
                CookingStep(1, "炸蟹脚", "蟹脚过油炸至裂壳捞出。", 240, null, null),
                CookingStep(2, "炒酱", "油锅下香料、辣椒爆香，加豆瓣酱炒出红油。", 120, null, null),
                CookingStep(3, "焖蟹脚", "蟹脚入锅翻炒，加啤酒焖10分钟至入味浓稠。", 600, null, null),
                CookingStep(4, "拌面", "热干面煮好装入碗中，浇蟹脚酱汁拌匀。", 120, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 35.0,
        ),
        Dish(
            id = "la_de_tiao",
            name = "辣得跳",
            tagline = "武汉夜市变态辣名菜，麻辣刺激",
            cuisine = "武汉夜宵",
            coverImageUrl = foodImage("辣得跳"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 25,
            calories = 350,
            ingredients = listOf(
                Ingredient("牛蛙", "400g", 25.0),
                Ingredient("干辣椒", "100g", 3.0),
                Ingredient("花椒", "30g", 1.5),
                Ingredient("蒜", "30g", 0.5),
                Ingredient("姜", "20g", 0.3),
                Ingredient("豆瓣酱", "20g", 1.0),
            ),
            description = "辣得跳是武汉夜市的变态辣名菜，牛蛙配大量干辣椒花椒爆炒，麻辣刺激到辣得跳起来。" +
                "牛蛙肉嫩滑入味，但辣度惊人，挑战者需要勇气。" +
                "是武汉夜市最能辣出名堂的一道菜。",
            article = Article(
                title = "辣得跳：武汉夜市的辣度天花板",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "名字就是警告",
                        "辣得跳顾名思义，辣到让人跳起来。这道菜源自武汉夜市，用大量干辣椒和花椒爆炒牛蛙，辣度远超常人承受范围。" +
                            "点菜前老板会反复确认你确定要这个辣度，是武汉夜市的辣度天花板。",
                    ),
                    ArticleSection(
                        "牛蛙嫩滑配重辣",
                        "辣得跳选用牛蛙为主料，肉质比鸡肉更嫩滑。" +
                            "但嫩滑的牛蛙裹上变态辣的酱汁，一口下去先是鲜嫩随即被麻辣冲击。配冰啤酒或酸梅汤是标配，解辣又过瘾。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "辣得跳教学", SAMPLE_VIDEO, foodImage("辣得跳"), 360),
            ),
            steps = listOf(
                CookingStep(1, "处理牛蛙", "牛蛙剁块，加料酒腌制去腥。", 180, null, null),
                CookingStep(2, "爆炒", "油锅下大量干辣椒、花椒、豆瓣酱爆香。", 120, null, null),
                CookingStep(3, "炒牛蛙", "牛蛙入锅大火翻炒至变色。", 180, null, null),
                CookingStep(4, "调味出锅", "加蒜、姜翻炒，调味出锅。", 60, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 55.0,
        ),
        Dish(
            id = "zha_zha",
            name = "炸炸",
            tagline = "武汉夜市油炸江湖，裹粉刷甜辣酱",
            cuisine = "武汉夜宵",
            coverImageUrl = foodImage("武汉炸炸"),
            difficulty = Difficulty.EASY,
            durationMinutes = 20,
            calories = 550,
            ingredients = listOf(
                Ingredient("各类荤素食材", "500g", 15.0),
                Ingredient("裹粉", "100g", 1.0),
                Ingredient("甜辣酱", "50g", 2.0),
                Ingredient("食用油", "500ml", 3.0),
                Ingredient("孜然", "10g", 0.3),
            ),
            description = "炸炸是武汉夜市的油炸江湖，荤素食材裹粉油炸，刷上甜辣酱撒孜然。" +
                "万物皆可炸，从藕片到鸡架，从年糕到火腿肠，炸出来刷酱就是美味。" +
                "这是武汉人最接地气的深夜街头味道。",
            article = Article(
                title = "炸炸：武汉夜市的油炸江湖",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "万物皆可炸",
                        "炸炸是武汉对油炸串串的叫法，一切食材都可以下油锅。" +
                            "藕片、土豆、年糕、鸡架、火腿肠裹上干粉入油锅炸至金黄，捞出刷甜辣酱撒孜然，简单粗暴却让人上瘾。",
                    ),
                    ArticleSection(
                        "甜辣酱是灵魂",
                        "炸炸好不好吃一半看酱，武汉炸炸的灵魂是甜辣酱。" +
                            "甜中带辣、辣中带甜，刷在刚出锅的炸物上，酱汁遇热激发香气。再撒一层孜然粉和辣椒粉，是武汉夜市最接地气的味道。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "武汉炸炸教学", SAMPLE_VIDEO, foodImage("武汉炸炸"), 300),
            ),
            steps = listOf(
                CookingStep(1, "备料", "各类荤素食材切块或切片，备好裹粉。", 180, null, null),
                CookingStep(2, "裹粉", "食材裹上干粉抖去多余。", 120, null, null),
                CookingStep(3, "油炸", "油锅七成热，食材分批炸至金黄酥脆。", 480, null, null),
                CookingStep(4, "刷酱", "捞出刷甜辣酱，撒孜然辣椒粉即可。", 60, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 25.0,
        ),
        Dish(
            id = "huang_pi_san_he",
            name = "黄陂三合",
            tagline = "武汉名菜，鱼丸、肉丸、肉糕三合一",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("黄陂三合"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 60,
            calories = 420,
            ingredients = listOf(
                Ingredient("鱼肉", "300g", 15.0),
                Ingredient("猪肉", "200g", 10.0),
                Ingredient("鸡蛋", "3个", 3.0),
                Ingredient("淀粉", "50g", 1.0),
                Ingredient("葱姜", "20g", 0.3),
            ),
            description = "黄陂三合是武汉黄陂区名菜，鱼丸、肉丸、肉糕三样合一锅，又名黄陂三鲜。" +
                "一菜三味三种口感，鱼丸嫩滑、肉丸弹牙、肉糕软香。" +
                "是黄陂人逢年过节宴客必备的团圆菜。",
            article = Article(
                title = "黄陂三合：一菜三味团圆菜",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "三合即三鲜",
                        "黄陂三合又称黄陂三鲜，由鱼丸、肉丸、肉糕三样组成。" +
                            "黄陂人逢年过节、婚丧嫁娶必做此菜，三样合一象征团圆美满。一锅出三味，是黄陂饮食文化的代表。",
                    ),
                    ArticleSection(
                        "三种手艺一锅成",
                        "鱼丸要手刮鱼蓉打至上劲，肉丸要手工剁肉摔打弹牙，肉糕要鱼肉混合蒸制定型。" +
                            "三样分别做好后合烧一锅，加高汤焖煮入味。鱼丸嫩、肉丸弹、肉糕香，口感层次丰富。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "黄陂三合教学", SAMPLE_VIDEO, foodImage("黄陂三合"), 720),
            ),
            steps = listOf(
                CookingStep(1, "做鱼丸", "鱼肉剁茸加蛋清淀粉搅打，挤丸入冷水。", 600, null, null),
                CookingStep(2, "做肉丸", "猪肉剁碎加调料摔打上劲，挤丸入油锅炸定型。", 600, null, null),
                CookingStep(3, "做肉糕", "鱼肉猪肉混合加蛋蒸制定型，切块。", 1200, null, null),
                CookingStep(4, "合烧", "三样入锅加高汤焖煮10分钟，调味出锅。", 600, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 45.0,
        ),
        Dish(
            id = "ba_gua_tang",
            name = "八卦汤",
            tagline = "武汉十大名菜之一，甲鱼煨汤，鲜醇滋补",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("八卦汤"),
            difficulty = Difficulty.HARD,
            durationMinutes = 120,
            calories = 250,
            ingredients = listOf(
                Ingredient("甲鱼", "1只", 70.0),
                Ingredient("枸杞", "15g", 2.0),
                Ingredient("党参", "10g", 1.5),
                Ingredient("老姜", "30g", 0.5),
                Ingredient("盐", "5g", 0.1),
            ),
            description = "八卦汤是武汉十大名菜之一，甲鱼煨汤鲜醇滋补，汤色浓白如乳。" +
                "加枸杞党参慢煨两小时，甲鱼裙边的胶质全融在汤里。" +
                "一碗汤端上来浓稠挂唇，是秋冬进补的鄂菜头号养生汤。",
            article = Article(
                title = "八卦汤：甲鱼煨出养生道",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "名菜的来历",
                        "八卦汤因甲鱼壳纹理似八卦图而得名，是武汉十大名菜之一。" +
                            "选用野生甲鱼配枸杞、党参等药材慢火煨制，汤汁浓白鲜醇，胶质感十足。是湖北人秋冬进补的首选汤品。",
                    ),
                    ArticleSection(
                        "慢煨出胶质",
                        "八卦汤讲究慢工出细活，甲鱼焯水后加足量水小火煨两小时以上。" +
                            "裙边的胶原蛋白慢慢溶入汤中，汤汁变得浓稠挂唇。只加盐调味不加香料，喝的是甲鱼本味的鲜醇。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "八卦汤教学", SAMPLE_VIDEO, foodImage("八卦汤"), 1200),
            ),
            steps = listOf(
                CookingStep(1, "处理甲鱼", "甲鱼宰杀去内脏，焯水去膜，剁块。", 600, null, null),
                CookingStep(2, "煨汤", "甲鱼块加老姜、枸杞、党参和足量水大火烧开，转小火煨2小时。", 7200, null, null),
                CookingStep(3, "调味", "加盐调味，撇去浮油即可。", 60, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 95.0,
        ),
        Dish(
            id = "hong_shao_hui_yu",
            name = "红烧鮰鱼",
            tagline = "不吃鮰鱼不知鱼味，长江鮰鱼无小刺",
            cuisine = "鄂菜",
            coverImageUrl = foodImage("红烧鮰鱼"),
            difficulty = Difficulty.MEDIUM,
            durationMinutes = 45,
            calories = 330,
            ingredients = listOf(
                Ingredient("鮰鱼", "1条", 55.0),
                Ingredient("姜蒜", "30g", 0.5),
                Ingredient("酱油", "30ml", 1.0),
                Ingredient("糖", "15g", 0.3),
                Ingredient("料酒", "20ml", 0.5),
                Ingredient("葱", "15g", 0.2),
            ),
            description = "红烧鮰鱼是鄂菜河鲜代表，长江鮰鱼无小刺、肉质蒜瓣状，红烧酱香浓郁。" +
                "不吃鮰鱼不知鱼味是老饕的评价，一口鲜嫩肥美令人折服。" +
                "鮰鱼胶质丰富，红烧后汤汁浓稠挂肉，下饭一绝。",
            article = Article(
                title = "不吃鮰鱼不知鱼味",
                author = "味道日历",
                sections = listOf(
                    ArticleSection(
                        "长江无刺鲜",
                        "鮰鱼长吻鮠产于长江，肉质肥美呈蒜瓣状，最妙的是没有小刺。" +
                            "苏轼曾赞粉红石首仍无骨雪白河豚不药人，将鮰鱼与河豚媲美。红烧最能体现鮰鱼的鲜嫩肥美。",
                    ),
                    ArticleSection(
                        "红烧出浓香",
                        "鮰鱼胶质丰富，红烧时鱼皮析出胶质让汤汁自然浓稠。" +
                            "两面煎黄后加酱油、糖、料酒小火烧15分钟，汤汁收浓挂满鱼身。鱼肉鲜嫩入味，酱汁拌饭是这道菜的精髓。",
                    ),
                ),
            ),
            videos = listOf(
                VideoAsset("v1", "红烧鮰鱼教学", SAMPLE_VIDEO, foodImage("红烧鮰鱼"), 540),
            ),
            steps = listOf(
                CookingStep(1, "处理鱼", "鮰鱼去内脏洗净，两面划刀。", 300, null, null),
                CookingStep(2, "煎鱼", "热锅凉油下鱼煎至两面微黄。", 240, null, null),
                CookingStep(3, "红烧", "加姜蒜、酱油、糖、料酒和热水，小火烧15分钟。", 900, null, null),
                CookingStep(4, "收汁", "大火收汁浓稠，撒葱花出锅。", 180, null, null),
            ),
            mealType = MealType.DINNER,
            estimatedCostYuan = 75.0,
        ),
    )
}
