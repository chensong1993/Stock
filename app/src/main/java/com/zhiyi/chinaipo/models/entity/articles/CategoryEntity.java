/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhiyi.chinaipo.models.entity.articles;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

/**
 * @param
 * @author chen
 * @note
 * @return
 */
public class CategoryEntity {
    private String SHORT_URL;

    private int id;

    private String name;

    private String nav_type;

    public void setSHORT_URL(String SHORT_URL){
        this.SHORT_URL = SHORT_URL;
    }
    public String getSHORT_URL(){
        return this.SHORT_URL;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setNav_type(String nav_type){
        this.nav_type = nav_type;
    }
    public String getNav_type(){
        return this.nav_type;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id &&
                Objects.equals(SHORT_URL, that.SHORT_URL) &&
                Objects.equals(name, that.name) &&
                Objects.equals(nav_type, that.nav_type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(SHORT_URL, id, name, nav_type);
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "SHORT_URL='" + SHORT_URL + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", nav_type='" + nav_type + '\'' +
                '}';
    }
}
