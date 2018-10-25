package com.marko.domain

import io.reactivex.FlowableTransformer
import io.reactivex.SingleTransformer

abstract class FlowableTransformer<T> : FlowableTransformer<T, T>

abstract class SingleTransformer<T> : SingleTransformer<T, T>