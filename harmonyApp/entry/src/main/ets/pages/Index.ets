// 
// TasteDaily 鸿蒙版 - 入口文件
// 
// HarmonyOS NEXT 使用 ArkTS + ArkUI 开发。
// 此文件是鸿蒙端的页面入口，展示日历主页。
// 业务逻辑通过共享 JSON 数据文件与 Android/iOS 端保持一致。
//

import { common } from '@kit.AbilityKit';
import { DateUtil } from '../utils/DateUtil';
import { DishData } from '../data/DishData';
import { CalendarComponent } from '../components/CalendarComponent';

@Entry
@Component
struct Index {
  @State selectedDate: string = DateUtil.today();
  @State currentMonth: string = DateUtil.currentMonth();
  @State dishForSelected: object | null = null;

  aboutToAppear() {
    this.loadDishForDate(this.selectedDate);
  }

  loadDishForDate(date: string) {
    // 使用与 Android/iOS 相同的选菜算法
    const dishIndex = DishData.getDishIndexForDate(date);
    this.dishForSelected = DishData.getDishByIndex(dishIndex);
    this.selectedDate = date;
  }

  build() {
    Column() {
      // 顶部标题栏
      Text('味道日历')
        .fontSize(24)
        .fontColor(Color.White)
        .width('100%')
        .height(56)
        .backgroundColor('#6750A4')
        .textAlign(TextAlign.Center)
        .padding(16)

      // 日历组件
      CalendarComponent({
        currentMonth: this.currentMonth,
        selectedDate: this.selectedDate,
        onDateSelected: (date: string) => {
          this.loadDishForDate(date);
        }
      })

      // 当日推荐菜卡片
      if (this.dishForSelected) {
        DishCard({ dish: this.dishForSelected })
      }
    }
    .width('100%')
    .height('100%')
  }
}

@Component
struct DishCard {
  @Prop dish: object;

  build() {
    Column() {
      Text(this.dish['name'])
        .fontSize(22)
        .fontWeight(FontWeight.Bold)
        .margin({ bottom: 8 })

      Text(this.dish['tagline'])
        .fontSize(14)
        .fontColor('#666666')
        .margin({ bottom: 12 })

      Row() {
        Text(this.dish['cuisine'])
          .fontSize(12)
          .backgroundColor('#E0E0E0')
          .padding(4)
          .borderRadius(8)
          .margin({ right: 8 })

        Text(this.dish['difficulty'])
          .fontSize(12)
          .backgroundColor('#E0E0E0')
          .padding(4)
          .borderRadius(8)
          .margin({ right: 8 })

        Text(this.dish['duration'])
          .fontSize(12)
          .backgroundColor('#E0E0E0')
          .padding(4)
          .borderRadius(8)
      }
    }
    .width('90%')
    .padding(16)
    .backgroundColor(Color.White)
    .borderRadius(16)
    .shadow({ radius: 8, color: 'rgba(0,0,0,0.1)' })
    .margin({ top: 16 })
  }
}
