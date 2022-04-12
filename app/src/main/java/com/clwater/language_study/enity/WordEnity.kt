package com.clwater.language_study.enity

/**
 * @author: gengzhibo
 * @date: 2022/4/2
 */

data class WordEnity(val id: Int,
                     val word: String,
                     val translate: String,
                     val eg: String ?,
                     val word_src: String ?,
                     val category: String ?,
                     val learn_order: Int,
                     val phonetic_symbol: String ?)