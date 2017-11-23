package com.pj.bos.service.base.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.bos.dao.base.IAreaDao;
import com.pj.bos.domain.base.Area;
import com.pj.bos.service.base.IAreaService;
import com.pj.bos.utils.PinYin4jUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
@Service
@Transactional
public class AreaServiceImpl implements IAreaService {
	@Autowired
	private IAreaDao dao;
	//低版本excel(.xls)导入数据库
	@Override
	public void importXls(File areaFile) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(areaFile));
			HSSFSheet sheet = workbook.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 1; i < lastRowNum; i++) {
				Area area = new Area();
				HSSFRow row = sheet.getRow(i);
				String id = row.getCell(0).getStringCellValue();
				area.setId(id);
				String province = row.getCell(1).getStringCellValue();
				area.setProvince(province);
				String city = row.getCell(2).getStringCellValue();
				area.setCity(city);
				String district = row.getCell(3).getStringCellValue();
				area.setDistrict(district);
				String postcode = row.getCell(4).getStringCellValue();
				area.setPostcode(postcode);
				
				province = province.substring(0, province.length()-1);
				city = city.substring(0, city.length()-1);
				district = district.substring(0, district.length()-1);
				//设置citycode,北京-->beijing
				String citycode = PinYin4jUtils.hanziToPinyin(city, "");
				area.setCitycode(citycode);
				//设置shortcode,河北石家庄新华-->HBSJZXH
				String[] headerChar = PinYin4jUtils.getHeadByString(province+city+district);
				String shortcode = StringUtils.join(headerChar);
				area.setShortcode(shortcode);
				//储存
				dao.save(area);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//分页查询
	@Override
	public Page<Area> findByPage(Pageable pageable) {
		return dao.findAll(pageable);
	}
}
