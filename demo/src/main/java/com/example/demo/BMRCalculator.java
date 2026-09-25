/**
 * 
 * @author กลุ่มหิว
 */

package com.example.demo;
// สืบทอดจากคลาส Body

public class BMRCalculator extends Body {
    private int age;
    private boolean isMale;

    /**
     * 
     * @param weight ค่าน้ำหนักของผู้ใช้ที่กรอกมา (กิโลกรัม)
     * @param height ค่าส่วนสูงของผู้ใช้ที่กรอกมา (เซนติเมตร)
     * @param age    ค่าอายุของผู้ใช้ที่กรอกมา (ปี)
     * @param isMale เพศของผู้ใช้ที่กรอกมา
     */
    public BMRCalculator(double weight, double height, int age, boolean isMale) {
        super(weight, height); // เรียก constructor ของคลาส Body
        this.age = age;
        this.isMale = isMale;
    }

    @Override
    // ฟังก์ชันคำนวณค่าอัตราการเผาผลาญพลังงาน
    public double calculate() {

        if (isMale) {
            // สูตรสำหรับเพศชาย
            return 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else {
            // สูตรสำหรับเพศหญิง
            return 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        }
    }
}
