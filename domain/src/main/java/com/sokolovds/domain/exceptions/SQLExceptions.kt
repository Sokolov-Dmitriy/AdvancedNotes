package com.sokolovds.domain.exceptions

sealed class AppException : RuntimeException()

class EmptyTitleException : AppException()

class OtherException : AppException()