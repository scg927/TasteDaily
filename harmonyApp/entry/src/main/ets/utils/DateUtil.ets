// 
// TasteDaily 鸿蒙版 - 日期工具类
//

export class DateUtil {
  static today(): string {
    const d = new Date();
    return this.formatDate(d);
  }

  static currentMonth(): string {
    const d = new Date();
    return `${d.getFullYear()}年${d.getMonth() + 1}月`;
  }

  static formatDate(d: Date): string {
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  /**
   * 获取某月的天数
   */
  static daysInMonth(year: number, month: number): number {
    return new Date(year, month, 0).getDate();
  }

  /**
   * 获取某月第一天是星期几（0=周日, 1=周一...）
   */
  static firstDayOfMonth(year: number, month: number): number {
    return new Date(year, month - 1, 1).getDay();
  }
}
