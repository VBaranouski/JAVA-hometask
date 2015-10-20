package com.tests.testsuite;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.tests.calculation.TestSortByCost;
import com.tests.giftbox.TestGift;
import com.tests.giftbox.TestWithDataProvider;

@RunWith(Categories.class)
@IncludeCategory(NegativeTests.class)// uncomment to run tests by Category
@Suite.SuiteClasses({ TestGift.class, TestSortByCost.class, TestWithDataProvider.class })

public class TestSuite {
}
