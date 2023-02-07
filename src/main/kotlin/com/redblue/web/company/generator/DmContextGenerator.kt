package com.redblue.web.company.generator

import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Executive
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.thymeleaf.context.Context
import java.text.SimpleDateFormat

@Component
class DmContextGenerator {
	fun defaultExecutiveExpireDmGenerate(company: Company, executives: List<Executive>?, user: LawFirmUser, master: List<Executive>?): Context {
		val address = if (StringUtils.hasText(company.deliveryPlace)) {
			company.deliveryPlace
		} else {
			company.companyAddress
		}
		return Context().apply {
			this.setVariable("lawFirm", user.lawFirm)
			this.setVariable("tel", "(대표), ${user.lawFirm!!.tel}")
			this.setVariable("postalCode", "<span>우)</span> <strong>${user.lawFirm!!.postalCode}</strong>")
			this.setVariable("docNum", "<span>문서번호 :</span> <strong>${company.registerNumber}</strong>")
			this.setVariable("exAddress", address)

			var companyNamePrefix = ""
			if (StringUtils.hasText(company.companyDivision)) {
				companyNamePrefix = "(" + company.companyDivision!!.slice(IntRange(0, 0)) + ")"
			}
			when (company.displayCompanyType) {
				Company.DisplayCompanyType.FRONT -> {
					this.setVariable("exCompany", companyNamePrefix + company.companyName)
				}
				Company.DisplayCompanyType.BACK -> {
					this.setVariable("exCompany", company.companyName + companyNamePrefix)
				}
				else -> {
					this.setVariable("exCompany", company.companyName)
				}
			}

			var masterInfo = master?.filter {
				it.position!!.contains("대표이사")
			}

			if (masterInfo != null) {
				if (masterInfo.isEmpty()) {
					masterInfo = master?.filter {
						it.position!!.contains("사내이사")
					}
				}
			}

			if (masterInfo != null) {
				if (masterInfo.isEmpty()) {
					masterInfo = master
				}
			}

			val postalCode = if (StringUtils.hasText(company.deliveryPlacePostalCode)) {
				company.deliveryPlacePostalCode
			} else {
				company.companyPostalCode
			}

			val masterName = if(masterInfo == null || masterInfo.isEmpty()) {
				""
			} else {
				masterInfo[0].name ?: ""
			}
			this.setVariable("exName", "대표이사 <strong>${masterName}</strong>님 귀하")
			this.setVariable("exPost", postalCode)
			this.setVariable("conTitle", "[제목 : <strong>임기만료 안내문</strong>]")
			var content0 = "귀사의 무궁한 발전을 기원합니다."
			var content1 = "임기만료안내"
			var content2 = "<div>" +
				"<p>안녕하십니까. <span>${user.lawFirm!!.representative}</span>법무사입니다.</p>" +
				"<p><strong>귀사의 임원임기가 아래와 같이 만료됩니다. 당 사무소에 등기업무를 맡겨 주시면, 성심성의껏 업무처리를 도와드리겠습니다.</strong></p>" +
				"</div>"
			this.setVariable("content0", content0)
			this.setVariable("content1", content1)
			this.setVariable("content2", content2)
			this.setVariable("hr", "아래")
			this.setVariable("tableTitle1", "직위")
			this.setVariable("tableTitle2", "성명")
			this.setVariable("tableTitle3", "만기일자")
			val formatter = SimpleDateFormat("yyyy년 MM월 dd일")
			executives?.forEach {
				if(it.position == "감사") {
					it.expiredAtString =  "정기주주총회종결일<br />(" + formatter.format(it.expiredAt) + "까지)"
				} else {
					it.expiredAtString = formatter.format(it.expiredAt)
				}
			}
			this.setVariable("executives", executives)
			this.setVariable("etc", "※참고사항")
			this.setVariable("etcContent0", "<b> 당 사무소에 등기업무를 의뢰할 경우, 직접 방문하여 처리해 드릴 수 있습니다.</b>")
			this.setVariable("etcContent1", "임기만료일로부터 2주 이내에 <b style=\"text-decoration: underline;\">임원변경등기를 하지 않으면 상법 제635조 제1항에 따라 500만원 이하의 과태료가 부과</b>되오니 각별히 유의하시기 바랍니다.")
			this.setVariable("etcContent2", "대표자(대표이사 또는 1인 사내이사)의 개인주소가 변경된 경우도 2주 이내에 변경등기를 하여야 합니다. 이를 위반하여 과태료를 부과 받는 회사들이 많으니, 특히 유의하시기 바랍니다.")
			this.setVariable("etcContent3", "위 정보는 공개된 법인등기부등본을 기초로 작성된 것이므로, 개인정보를 침해한 사실이 전혀 없음을 밝혀 드립니다.")
			this.setVariable("content3", "<span class=\"font-other\">담당자 이메일(dkdltm2468@naver.com) 또는 팩스로 법인등기부등본, 주주명부, 정관 사본을 미리 보내주시면, 보다 신속하고 정확한 상담 및 서류 안내가 가능합니다.</span>")
			this.setVariable("desc", "\"당사무소는 법인등기 외 가압류, 가처분, 소송, 부동산등기 업무 등 각종 법률사무를 취급하오니, 이에 대하여 귀사의 문의가 있을 경우 정성과 책임을 다하여 상담하여 드리겠습니다.\"")
		}
	}


	fun lawAndOfficeExecutiveExpireDmGenerate(company: Company, executives: List<Executive>?, user: LawFirmUser, master: List<Executive>?): Context {
		val address = if (StringUtils.hasText(company.deliveryPlace)) {
			company.deliveryPlace
		} else {
			company.companyAddress
		}
		return Context().apply {
			this.setVariable("lawFirm", user.lawFirm)
			this.setVariable("tel", "(대표), ${user.lawFirm!!.tel}")
			this.setVariable("postalCode", "<span>우)</span> <strong>${user.lawFirm!!.postalCode}</strong>")
			this.setVariable("regNum", "<span>등기번호 :</span> <strong>${company.registerNumber}</strong>")
			this.setVariable("exAddress", address)

			var companyNamePrefix = ""
			if (StringUtils.hasText(company.companyDivision)) {
				companyNamePrefix = "(" + company.companyDivision!!.slice(IntRange(0, 0)) + ")"
			}
			when (company.displayCompanyType) {
				Company.DisplayCompanyType.FRONT -> {
					this.setVariable("exCompany", companyNamePrefix + company.companyName)
				}
				Company.DisplayCompanyType.BACK -> {
					this.setVariable("exCompany", company.companyName + companyNamePrefix)
				}
				else -> {
					this.setVariable("exCompany", company.companyName)
				}
			}

			var masterInfo = master?.filter {
				it.position!!.contains("대표이사")
			}

			if (masterInfo != null) {
				if (masterInfo.isEmpty()) {
					masterInfo = master?.filter {
						it.position!!.contains("사내이사")
					}
				}
			}

			if (masterInfo != null) {
				if (masterInfo.isEmpty()) {
					masterInfo = master
				}
			}

			val postalCode = if (StringUtils.hasText(company.deliveryPlacePostalCode)) {
				company.deliveryPlacePostalCode
			} else {
				company.companyPostalCode
			}
			this.setVariable("exName", "대표이사 <strong>${masterInfo?.get(0)?.name}</strong>님 귀하")
			this.setVariable("exPost", postalCode)
			this.setVariable("conTitle", "<strong>임/기/만/료 안/내/문</strong>")
			this.setVariable("conTitleName", company.companyName + " " + masterInfo?.get(0)?.name)
			var box = "<strong>코로나19 대응</strong>을 위한 <br/>준비서류 <strong>방문수령, 등기우편&middot;온라인<br/>전자신청</strong><span>(공인인증서 사용)</span> 가능합니다."
			var warning = "<strong>임기만료, 대표님의 주소변경</strong>으로 인한 변경등기는 <strong>2주</strong> 이내에 하셔야 하며, 등기를 늦게 할 경우 상법상 과태료(<em>최고 500만원까지</em>)가 <em>대표님 개인에게 부과</em>될 수 있습니다."
			var content0 = "귀사의 무궁한 발전을 기원합니다."
			var content1 = "당월에 아래와 같이 귀사의 임원임기가 만료되었음을 안내해 드립니다."
			var content2 = "※위 정보는 일반에 공개된 법인등기부에 의해 작성된 것이며, 개인정보침해가 전혀 없음을 밝혀드립니다."
			this.setVariable("box", box)
			this.setVariable("warning", warning)
			this.setVariable("content0", content0)
			this.setVariable("content1", content1)
			this.setVariable("content2", content2)
			this.setVariable("hr", "아래")
			this.setVariable("tableTitle1", "직위")
			this.setVariable("tableTitle2", "성명")
			this.setVariable("tableTitle3", "만기일자")
			val formatter = SimpleDateFormat("yyyy년 MM월 dd일")
			executives?.forEach {
				if(it.expiredAt != null) {
					if(it.position == "감사") {
						it.expiredAtString =  "정기주주총회종결일<br />(" + formatter.format(it.expiredAt) + "까지)"
					} else {
						it.expiredAtString = formatter.format(it.expiredAt)
					}
				}
			}
			this.setVariable("executives", executives)
			this.setVariable("etc", "▣ 준비서류 (구체적인 준비서류는 달라질 수 있습니다)")
			this.setVariable("etc1", "① 법인등기부등본 1부, 사업자등록증사본 1부, 정관사본 2부, 주주명부 1부")
			this.setVariable("etc2", "② 법인인감증명서 1부, 법인인감도장, 법인인감카드, 대표자인감도장, 인감증명서 1부")
			this.setVariable("etc3", "③ 위 주주명부에 의거하여 총주식수의 1/3에 달하도록 주주님의 인감증명서 1부, 인감도장")
			this.setVariable("etc4", "④ 임기만료 된 임원님의 인감증명서 2부, 주민등록등(초)본 1부, 인감도장")
			this.setVariable("etc5", "⑤ 대표이사님의 임기가 만료된 경우 이사님 과반수 인감증명서 1부, 인감도장")
			this.setVariable("etc6", "※인감증명서, 준비서류는 3개월 이내에 발급된 것이어야 합니다.")
			this.setVariable("etc7", "※위 서류는 전원 연임할 경우입니다. 자세한 안내는 전화를 주시면 바로 안내해 드리겠습니다.")
			this.setVariable("bottom1", "이론에 능하고 실무에 강한 로앤 www.lawand.co.kr")
			this.setVariable("bottom2", "전문화된 이론과 다양한 실무경험을 겸비한 로앤은 2,000개사 지역 법인과 함께 성장해 오고 있습니다.")
			this.setVariable("bottom3", "기업법무 / 출자전환 / 현물출자 / 가수금증자 / 자기주식취득 / 부동산등기(취득세) <br/> 개인회생&middot;파산 / 채권관리&middot;추심 / 압류&middot;추심 / 성년후견 / 유언(상속)대용신탁")
		}
	}

}
