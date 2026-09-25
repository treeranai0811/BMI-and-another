/**
 * 
 * @author กลุ่มหิว
 */

package com.example.demo;
// สืบทอดจากคลาส Body

public class IBWCalculator extends Body {
    /**
     * 
     * @param height ส่วนสูงของผู้ใช้ที่กรอกเข้ามา (เชนติเมตร)
     * @param isMale เพศของผู้ใช้ที่กรอกเข้ามา
     */
    public IBWCalculator(double height, boolean isMale) {
        super(0, height); // ตัวแปรน้ำหนักไม่ใช้ในสูตรนี้ จึงตั้งเป็น 0
        this.height = height;
        this.isMale = isMale;
    }

    private boolean isMale;

    // ฟังก์ชันคำนวณค่าน้ำหนักที่ควรจะเป็น
    @Override
    public double calculate() {

        if (isMale) {
            // สูตรของเพศชาย
            return height - 100; // สูตรสำหรับเพศชาย
        } else {
            // สูตรของเพศหญิง
            return height - 105; // สูตรสำหรับเพศหญิง
        }
    }
}
