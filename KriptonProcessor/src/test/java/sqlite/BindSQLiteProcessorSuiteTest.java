/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bind.kripton42faster.TestKripton42;
import bind.kripton70.TestRuntime70Suite;
import bind.kripton71List.TestCompile71;
import bind.kripton72.TestCompile72;
import bind.kripton73Array.TestCompile73;
import shared.kripton45.TestKripton45;
import shared.kripton46.TestKripton46;
import shared.kripton47.Test47Suite;
import sqlite.example01.SQLiteProcessorTest;
import sqlite.kripton33.TestKripton33;
import sqlite.kripton38.TestKripton38;
import sqlite.kripton40.TestKripton40;
import sqlite.kripton41.TestKripton41;
import sqlite.kripton48.TestKripton48;
import sqlite.kripton49.TestKripton49;
import sqlite.kripton50.TestKripton50;
import sqlite.kripton56.TestKripton56;
import sqlite.kripton58.TestKripton58Array;
import sqlite.kripton58.TestKripton58List;
import sqlite.kripton60.TestKripton60;
import sqlite.kripton62.TestKripton62;
import sqlite.kripton63.TestKripton63;
import sqlite.kripton64.Test64Suite;
import sqlite.kripton84.Test84Suite;
import sqlite.test01.TestDatabase01;
import sqlite.test02.TestDao01;
import sqlite.test03.Test03;
import sqlite.test05firt_aid.TestFirstAid;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ TestDatabase01.class,
			SQLiteProcessorTest.class,
			TestDao01.class, 
			Test03.class, 
			TestKripton33.class, TestKripton38.class, TestKripton40.class, TestKripton41.class, 
			TestKripton42.class, TestKripton45.class, TestKripton46.class, Test47Suite.class,
			TestKripton48.class, TestKripton49.class, TestKripton50.class, TestKripton56.class,
			TestKripton58Array.class,TestKripton58List.class,
			TestKripton60.class,
			TestKripton62.class,
			TestKripton63.class,
			Test64Suite.class,
			TestRuntime70Suite.class,
			TestCompile71.class,
			TestCompile72.class,
			TestCompile73.class,
			Test84Suite.class,
		TestFirstAid.class })
//@formatter:on
public class BindSQLiteProcessorSuiteTest {

}
