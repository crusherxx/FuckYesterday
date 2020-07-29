package com.example.task;

import javax.xml.transform.Result;

interface ITaskBackground<Result> {

    Result onBackground();
}
