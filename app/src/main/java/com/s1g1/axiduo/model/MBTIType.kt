package com.s1g1.axiduo.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.s1g1.axiduo.R

enum class MBTIType(
    val typeName: String,
    @StringRes val nickname: Int,
    @StringRes val description: Int,
    @DrawableRes val img: Int = R.drawable.no_image
){
    INTJ(
        typeName = "INTJ",
        nickname = R.string.mbti_type_intj_nickname,
        description = R.string.mbti_type_intj_description,
        img = R.drawable.intj
    ),

    INTP(
        typeName = "INTP",
        nickname = R.string.mbti_type_intp_nickname,
        description = R.string.mbti_type_intp_description,
        img = R.drawable.intp
    ),

    ENTJ(
        typeName = "ENTJ",
        nickname = R.string.mbti_type_entj_nickname,
        description = R.string.mbti_type_entj_description,
        img = R.drawable.entj
    ),

    ENTP(
        typeName = "ENTP",
        nickname = R.string.mbti_type_entp_nickname,
        description = R.string.mbti_type_entp_description,
        img = R.drawable.entp
    ),

    INFJ(
        typeName = "INFJ",
        nickname = R.string.mbti_type_infj_nickname,
        description = R.string.mbti_type_infj_description,
        img = R.drawable.infj
    ),

    INFP(
        typeName = "INFP",
        nickname = R.string.mbti_type_infp_nickname,
        description = R.string.mbti_type_infp_description,
        img = R.drawable.infp
    ),

    ENFJ(
        typeName = "ENFJ",
        nickname = R.string.mbti_type_enfj_nickname,
        description = R.string.mbti_type_enfj_description,
        img = R.drawable.enfj
    ),

    ENFP(
        typeName = "ENFP",
        nickname = R.string.mbti_type_enfp_nickname,
        description = R.string.mbti_type_enfp_description,
        img = R.drawable.enfp
    ),

    ISTJ(
        typeName = "ISTJ",
        nickname = R.string.mbti_type_istj_nickname,
        description = R.string.mbti_type_istj_description,
        img = R.drawable.istj
    ),

    ISFJ(
        typeName = "ISFJ",
        nickname = R.string.mbti_type_isfj_nickname,
        description = R.string.mbti_type_isfj_description,
        img = R.drawable.isfj
    ),

    ESTJ(
        typeName = "ESTJ",
        nickname = R.string.mbti_type_estj_nickname,
        description = R.string.mbti_type_estj_description,
        img = R.drawable.estj
    ),

    ESFJ(
        typeName = "ESFJ",
        nickname = R.string.mbti_type_esfj_nickname,
        description = R.string.mbti_type_esfj_description,
        img = R.drawable.esfj
    ),

    ISTP(
        typeName = "ISTP",
        nickname = R.string.mbti_type_istp_nickname,
        description = R.string.mbti_type_istp_description,
        img = R.drawable.istp
    ),

    ISFP(
        typeName = "ISFP",
        nickname = R.string.mbti_type_isfp_nickname,
        description = R.string.mbti_type_isfp_description,
        img = R.drawable.isfp
    ),

    ESTP(
        typeName = "ESTP",
        nickname = R.string.mbti_type_estp_nickname,
        description = R.string.mbti_type_estp_description,
        img = R.drawable.estp
    ),

    ESFP(
        typeName = "ESFP",
        nickname = R.string.mbti_type_esfp_nickname,
        description = R.string.mbti_type_esfp_description,
        img = R.drawable.esfp
    );
    companion object{
        fun fromString(typeString: String) : MBTIType?{
            return entries.find{ it.typeName == typeString}
        }
    }
}