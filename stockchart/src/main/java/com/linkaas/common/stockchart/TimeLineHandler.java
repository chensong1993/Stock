/*
 * Copyright (C) 2017 Apache Open Source Project
 *
 *      https://wordplat.com/InteractiveKLineView/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.linkaas.common.stockchart;

import android.view.MotionEvent;

import com.linkaas.common.stockchart.entry.Entry;

/**
 * <p>SimpleKLineHandler</p>
 * <p>Date: 2017/3/22</p>
 *
 * @author zhangzhi
 */

public interface TimeLineHandler {

    void onSingleTap(MotionEvent e, float x, float y);

    void onDoubleTap(MotionEvent e, float x, float y);

    void onHighlight(Entry entry, int entryIndex, float x, float y);

    void onCancelHighlight();

}
