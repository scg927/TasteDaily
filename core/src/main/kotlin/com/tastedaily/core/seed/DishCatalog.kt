package com.tastedaily.core.seed

import com.tastedaily.core.model.Article
import com.tastedaily.core.model.ArticleSection
import com.tastedaily.core.model.CookingStep
import com.tastedaily.core.model.Difficulty
import com.tastedaily.core.model.Dish
import com.tastedaily.core.model.Ingredient
import com.tastedaily.core.model.VideoAsset
import java.net.URLEncoder

/**
 * Built-in recipe catalog used as the demo data source. In production this would come from a
 * backend that publishes a new dish every day; see [com.tastedaily.core.domain.DailyDishSelector].
 */
object DishCatalog {

    /** Sample MP4 used for cooking-step / preview videos in the demo build. */
    private const val SAMPLE_VIDEO =
        "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny.mp4"

    private fun foodImage(prompt: String): String =
        "https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image" +
            "?prompt=" + URLEncoder.encode("$prompt，中式美食摄影，俯视，暖色调，专业摆盘", "UTF-8") +
            "&image_size=landscape_4_3"

    val all: List<Dish> = listOf(
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
                Ingredient("鸡胸肉", "300 克"),
                Ingredient("熟花生米", "80 克"),
                Ingredient("干辣椒", "10 克"),
                Ingredient("花椒", "3 克"),
                Ingredient("葱白", "30 克"),
                Ingredient("生抽", "2 勺"),
                Ingredient("香醋", "1 勺"),
                Ingredient("白糖", "1 勺"),
                Ingredient("淀粉", "1 勺"),
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
                Ingredient("鸡蛋", "4 个"),
                Ingredient("番茄", "2 个（约 300 克）"),
                Ingredient("葱花", "20 克"),
                Ingredient("盐", "适量"),
                Ingredient("白糖", "1 小勺"),
                Ingredient("食用油", "2 勺"),
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
                Ingredient("带皮五花肉", "500 克"),
                Ingredient("冰糖", "40 克"),
                Ingredient("生抽", "3 勺"),
                Ingredient("老抽", "1 勺"),
                Ingredient("料酒", "2 勺"),
                Ingredient("姜片", "20 克"),
                Ingredient("八角", "2 颗"),
                Ingredient("热水", "适量"),
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
                Ingredient("嫩豆腐", "1 盒（约 400 克）"),
                Ingredient("牛肉末", "100 克"),
                Ingredient("郫县豆瓣酱", "1.5 勺"),
                Ingredient("豆豉", "1 勺"),
                Ingredient("花椒粉", "适量"),
                Ingredient("蒜苗", "30 克"),
                Ingredient("水淀粉", "适量"),
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
                Ingredient("猪里脊", "250 克"),
                Ingredient("水发木耳", "60 克"),
                Ingredient("冬笋", "80 克"),
                Ingredient("泡红椒", "30 克"),
                Ingredient("葱姜蒜末", "共 30 克"),
                Ingredient("生抽", "2 勺"),
                Ingredient("香醋", "3 勺"),
                Ingredient("白糖", "2 勺"),
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
                Ingredient("细面条", "200 克"),
                Ingredient("小葱", "150 克"),
                Ingredient("食用油", "100 毫升"),
                Ingredient("生抽", "3 勺"),
                Ingredient("老抽", "1 勺"),
                Ingredient("白糖", "1.5 勺"),
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
        ),
    )
}
