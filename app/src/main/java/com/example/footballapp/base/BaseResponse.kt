package com.example.footballapp.base

import com.example.footballapp.data.team.remote.response.Team

data class BaseResponse(
    val teams: List<Team>?
)