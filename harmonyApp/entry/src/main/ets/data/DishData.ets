// 
// TasteDaily 鸿蒙版 - 菜品数据与选菜算法
// 与 Android/iOS 的 DailyDishSelector 保持一致的逻辑
//

export class DishData {
  // 菜谱数据（与 core/seed/DishCatalog.kt 一致）
  private static readonly DISHES: object[] = [
    { id: 'kung_pao', name: '宫保鸡丁', tagline: '川菜经典，麻辣鲜香', cuisine: '川菜', difficulty: '中等', duration: '20分钟' },
    { id: 'tomato_egg', name: '番茄炒蛋', tagline: '国民家常菜，酸甜下饭', cuisine: '家常菜', difficulty: '简单', duration: '10分钟' },
    { id: 'red_braised_pork', name: '红烧肉', tagline: '肥而不腻，入口即化', cuisine: '家常菜', difficulty: '中等', duration: '60分钟' },
    { id: 'mapo_tofu', name: '麻婆豆腐', tagline: '麻、辣、烫、香、酥、嫩、鲜、活', cuisine: '川菜', difficulty: '中等', duration: '25分钟' },
    { id: 'yu_xiang_pork', name: '鱼香肉丝', tagline: '没有鱼的鱼香味', cuisine: '川菜', difficulty: '中等', duration: '20分钟' },
    { id: 'scallion_noodles', name: '葱油拌面', tagline: '简单到极致的美味', cuisine: '上海菜', difficulty: '简单', duration: '15分钟' },
    { id: 'pork_belly_potato', name: '五花肉炒土豆片', tagline: '焦香下饭，土豆吸饱肉香', cuisine: '家常菜', difficulty: '简单', duration: '25分钟' },
    { id: 'red_braised_fish', name: '红烧鱼块', tagline: '酱汁浓稠，鱼肉鲜嫩入味', cuisine: '家常菜', difficulty: '中等', duration: '35分钟' },
    { id: 'tomato_meatball_soup', name: '番茄汽水丸子汤', tagline: '酸甜开胃，丸子Q弹多汁', cuisine: '家常菜', difficulty: '中等', duration: '30分钟' },
    { id: 'hand_torn_cabbage', name: '手撕包菜', tagline: '脆爽开胃，三分钟出锅', cuisine: '湘菜', difficulty: '简单', duration: '10分钟' },
    { id: 'chestnut_chicken', name: '板栗烧鸡', tagline: '板栗粉糯，鸡肉鲜香', cuisine: '家常菜', difficulty: '中等', duration: '45分钟' },
    { id: 'sweet_sour_ribs', name: '糖醋排骨', tagline: '酸甜适口，色泽红亮', cuisine: '家常菜', difficulty: '中等', duration: '50分钟' },
    { id: 'mala_xiangguo', name: '麻辣香锅', tagline: '一锅烩万物，麻辣过瘾', cuisine: '川菜', difficulty: '中等', duration: '40分钟' },
    { id: 'meicai_kourou', name: '梅菜扣肉', tagline: '肉烂味浓，梅菜吸油增香', cuisine: '客家菜', difficulty: '较难', duration: '120分钟' },
    { id: 'wanzhou_grilled_fish', name: '万州烤鱼（草鱼）', tagline: '先烤后炖，麻辣鲜香一锅端', cuisine: '川菜', difficulty: '较难', duration: '70分钟' },
    { id: 'steamed_pork_potato', name: '粉蒸五花肉土豆', tagline: '米粉裹肉，土豆垫底吸香', cuisine: '家常菜', difficulty: '中等', duration: '60分钟' },
    { id: 'steamed_wuchang_fish', name: '清蒸武昌鱼', tagline: '原汁原味，鲜嫩清甜', cuisine: '鄂菜', difficulty: '简单', duration: '20分钟' },
    { id: 'stir_fried_okra', name: '清炒秋葵', tagline: '清爽脆嫩，三分钟上桌', cuisine: '家常菜', difficulty: '简单', duration: '10分钟' },
  ];

  private static readonly EPOCH = new Date(2024, 0, 1); // 2024-01-01

  /**
   * 选菜算法：与 Kotlin DailyDishSelector 完全一致
   * 距 epoch 天数 % 菜池大小 = 菜品索引
   */
  static getDishIndexForDate(dateStr: string): number {
    const date = new Date(dateStr);
    const daysSinceEpoch = Math.floor((date.getTime() - this.EPOCH.getTime()) / (1000 * 60 * 60 * 24));
    const poolSize = this.DISHES.length;
    const idx = ((daysSinceEpoch % poolSize) + poolSize) % poolSize; // floorMod
    return idx;
  }

  static getDishByIndex(index: number): object {
    return this.DISHES[index];
  }

  static getAllDishes(): object[] {
    return this.DISHES;
  }
}
