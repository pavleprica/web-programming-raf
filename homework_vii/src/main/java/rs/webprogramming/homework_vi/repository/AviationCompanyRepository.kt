package rs.webprogramming.homework_vi.repository

import rs.webprogramming.homework_vi.model.AviationCompany

interface AviationCompanyRepository {

    fun getAll(): List<AviationCompany>

    fun findById(id: Long): AviationCompany

    fun save(aviationCompany: AviationCompany)

    fun checkVersionCompatibility(id: Long, version: Long): Boolean

}