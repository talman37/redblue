package com.redblue.web.company.service

import com.redblue.web.company.model.Company
import com.redblue.web.company.model.dto.CompanyExcelDto
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

@Service
class CompanyExcelService {

	fun generate(companies: List<Company>): ByteArrayInputStream {

		val workbook = XSSFWorkbook()
		val out = ByteArrayOutputStream()

		val createHelper = workbook.creationHelper
		val companySheet = workbook.createSheet("법인")

		val headerFont = workbook.createFont()
		headerFont.bold = true
		val headerCellStyle = workbook.createCellStyle()
		headerCellStyle.setFont(headerFont)

		val headerRow = companySheet.createRow(0)
		val companyExcelClass = CompanyExcelDto::class
		companyExcelClass.primaryConstructor?.parameters?.forEachIndexed { i, property ->
			val cell = headerRow.createCell(i)
			cell.setCellValue(this.changeCellName(property.name!!))
			cell.cellStyle = headerCellStyle
		}

		val dateCellStyle = workbook.createCellStyle()
		dateCellStyle.dataFormat = createHelper.createDataFormat().getFormat("yyyy-MM-dd")

		val companiesExcelDtoList = CompanyExcelDto.toList(companies)

		var rowIndex = 1
		for (companyExcelDto in companiesExcelDtoList) {
			val row = companySheet.createRow(rowIndex++)
			row.createCell(0).setCellValue(companyExcelDto.registerOffice)
			companyExcelDto.registerNumber?.let {
				row.createCell(1).setCellValue(it.toDouble())
			} ?: row.createCell(1)
			companyExcelDto.companyNumber1?.let {
				row.createCell(2).setCellValue(it.toDouble())
			} ?: row.createCell(2)
			companyExcelDto.companyNumber2?.let {
				row.createCell(3).setCellValue(it.toDouble())
			} ?: row.createCell(3)
			row.createCell(4).setCellValue(companyExcelDto.companyDivision)
			row.createCell(5).setCellValue(companyExcelDto.companyManageNumber)
			row.createCell(6).setCellValue(companyExcelDto.companyManageState)
			row.createCell(7).setCellValue(companyExcelDto.companyState)
			row.createCell(8).setCellValue(companyExcelDto.companyName)
			row.createCell(9).setCellValue(companyExcelDto.companyUpdatedAt)
			row.createCell(10).setCellValue(companyExcelDto.businessNumber)
			row.createCell(11).setCellValue(companyExcelDto.businessType)
			row.createCell(12).setCellValue(companyExcelDto.businessCondition)
			row.createCell(13).setCellValue(companyExcelDto.deliveryPlace)
			row.createCell(14).setCellValue(companyExcelDto.deliveryPlacePostalCode)
			row.createCell(15).setCellValue(companyExcelDto.companySubName)
			row.createCell(16).setCellValue(companyExcelDto.companyAddress)
			row.createCell(17).setCellValue(companyExcelDto.companyPostalCode)
			row.createCell(18).setCellValue(companyExcelDto.companyAddressUpdatedAt)
			row.createCell(19).setCellValue(companyExcelDto.noticeWay)
			row.createCell(20).setCellValue(companyExcelDto.noticeWayUpdatedAt)
			row.createCell(21).setCellValue(companyExcelDto.etc)
			row.createCell(22).setCellValue(companyExcelDto.convertibleBond)
			row.createCell(23).setCellValue(companyExcelDto.stockPurchaseOption)
			val companyFormationAtCell = row.createCell(24)
			companyFormationAtCell.setCellValue(companyExcelDto.companyFormationAt)
			companyFormationAtCell.cellStyle = dateCellStyle
			row.createCell(25).setCellValue(companyExcelDto.registerRecordCreateReason)
			val registerRecordCreateAtCell = row.createCell(26)
			registerRecordCreateAtCell.setCellValue(companyExcelDto.registerRecordCreateAt)
			registerRecordCreateAtCell.cellStyle = dateCellStyle
			row.createCell(27).setCellValue(this.covertBoolean(companyExcelDto.isHeadOfficeTransfer))
			val headOfficeTransferAtCell = row.createCell(28)
			headOfficeTransferAtCell.setCellValue(companyExcelDto.headOfficeTransferAt)
			headOfficeTransferAtCell.cellStyle = dateCellStyle
			row.createCell(29).setCellValue(this.covertBoolean(companyExcelDto.isDisband))
			val disbandAtCell = row.createCell(30)
			disbandAtCell.setCellValue(companyExcelDto.disbandAt)
			disbandAtCell.cellStyle = dateCellStyle
			row.createCell(31).setCellValue(companyExcelDto.disbandDeemedAt)
			row.createCell(32).setCellValue(this.covertBoolean(companyExcelDto.isLiquidation))
			val liquidationAtCell = row.createCell(33)
			liquidationAtCell.setCellValue(companyExcelDto.liquidationAt)
			liquidationAtCell.cellStyle = dateCellStyle
			row.createCell(34).setCellValue(this.covertBoolean(companyExcelDto.isRegisterRecordClosure))
			val registerRecordClosureAtCell = row.createCell(35)
			registerRecordClosureAtCell.setCellValue(companyExcelDto.registerRecordClosureAt)
			registerRecordClosureAtCell.cellStyle = dateCellStyle
			companyExcelDto.settlementMonth?.let {
				row.createCell(36).setCellValue(it.toDouble())
			} ?: row.createCell(36)
			row.createCell(37).setCellValue(companyExcelDto.recommender)
		}
		workbook.write(out)
		return ByteArrayInputStream(out.toByteArray())

	}

	private fun changeCellName(name: String): String {
		return when(name) {
			"registerOffice" -> "관할등기소"
			"registerNumber" -> "등기번호"
			"companyNumber1" -> "등록번호1"
			"companyNumber2" -> "등록번호2"
			"companyName" -> "상호"
			"companyDivision" -> "법인구분"
			"companyManageNumber" -> "법인관리번호"
			"companyManageState" -> "관리상태"
			"companyState" -> "법인상태"
			"companyUpdatedAt" -> "상호변경일"
			"businessNumber" -> "사업자등록번호"
			"businessType" -> "업종"
			"businessCondition" -> "업태"
			"deliveryPlace" -> "송달장소"
			"deliveryPlacePostalCode" -> "송달장소 우편번호"
			"companySubName" -> "병기상호"
			"companyAddress" -> "본점주소"
			"companyPostalCode" -> "본점 주소 우편번호"
			"companyAddressUpdatedAt" -> "본점주소변경일"
			"noticeWay" -> "공고방법"
			"noticeWayUpdatedAt" -> "공고방법변경일"
			"etc" -> "기타사항"
			"convertibleBond" -> "전환사채"
			"stockPurchaseOption" -> "주식매수선택권"
			"companyFormationAt" -> "회사성립연월일"
			"registerRecordCreateReason" -> "등기기록개설사유"
			"registerRecordCreateAt" -> "등기기록개설연월일"
			"isHeadOfficeTransfer" -> "본점전출여부"
			"headOfficeTransferAt" -> "본점전출연월일"
			"isDisband" -> "해산여부"
			"disbandAt" -> "해산일"
			"disbandDeemedAt" -> "해산간주일"
			"isLiquidation" -> "청산여부"
			"liquidationAt" -> "청산일"
			"isRegisterRecordClosure" -> "등기기록폐쇄여부"
			"registerRecordClosureAt" -> "등기기록폐쇄일"
			"settlementMonth" -> "결산기일"
			"recommender" -> "추천인"
			else -> "NoneTitle"
		}

	}

	private fun covertBoolean(value: Boolean?): String {
		return value?.let {
			if(it) "Y" else "N"
		} ?: "N"
	}

}