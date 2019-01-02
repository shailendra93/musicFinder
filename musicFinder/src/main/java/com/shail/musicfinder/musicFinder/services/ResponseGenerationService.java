package com.shail.musicfinder.musicFinder.services;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

//import com.topeng.tsg.module.helpersdk.objects.GenericResponse;

@Service
public class ResponseGenerationService {

	public DeferredResult<ResponseEntity<?>> generateDefferedResponse() {
		final DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>(50000l);
		deferredResult.onTimeout(new Runnable() {

			@Override
			public void run() { // Retry on timeout
				deferredResult.setErrorResult(
						ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
			}
		});
		return deferredResult;
	}

	public void sendTrueResponse(DeferredResult<ResponseEntity<?>> deferedResult, Future responseFuture) {
		GenericResponse response = new GenericResponse(true);
		try {
			response.setData((Serializable) responseFuture.get());
			deferedResult.setResult(ResponseEntity.ok(response));
		} catch (InterruptedException | ExecutionException e) {
			sendFalseResponse(deferedResult, e);
			e.printStackTrace();
		}

	}

	public void sendFalseResponse(DeferredResult<ResponseEntity<?>> deferedResult, Exception e) {
		GenericResponse response = new GenericResponse(false);
		response.setErrMsg(e.getMessage());
		deferedResult.setResult(ResponseEntity.ok(response));
	}

}
