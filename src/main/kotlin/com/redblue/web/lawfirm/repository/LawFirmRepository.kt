package com.redblue.web.lawfirm.repository

import com.redblue.web.lawfirm.model.LawFirm
import org.springframework.data.jpa.repository.JpaRepository

interface LawFirmRepository: JpaRepository<LawFirm, String>{
}