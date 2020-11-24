package com.example.foody2.Controller;

import com.example.foody2.Model.ThanhVienModel;

public class DangKyController  {
    ThanhVienModel thanhVienModel;
    public void DangKyController(){
         thanhVienModel=new ThanhVienModel();
    }
    public void ThemThongTinThanhVien(ThanhVienModel thanhVienModel,String uid){
            thanhVienModel.ThemThongTinThanhVien(thanhVienModel,uid);
    }
}
