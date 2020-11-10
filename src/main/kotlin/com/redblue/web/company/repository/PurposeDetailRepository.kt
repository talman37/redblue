package com.redblue.web.company.repository

import com.redblue.web.company.model.PurposeDetail
import org.springframework.data.jpa.repository.JpaRepository

interface PurposeDetailRepository: JpaRepository<PurposeDetail, String> {
}