package com.example.http;

import com.example.http.Paser.IParser;
import com.example.http.request.IRequest;
import com.example.http.request.call.ICall;
import com.example.http.response.IResponse;
import com.example.http.result.IResult;

public abstract class HttpScheduler {
    public abstract ICall newCall(IRequest request);

    public IResult execute(ICall call){
        IResponse iResponse = call.execute ();
        IRequest request = call.getRequest ();
        IParser parser = request.getParser ();
        return parser.parserResponse (request,iResponse);
    }
}
