package com.laozhu.f3knet;

import android.util.Log;

import com.laozhu.proto.Competition;
import com.laozhu.proto.CompetitionServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class CompetitionClient {
    final CompetitionServiceGrpc.CompetitionServiceStub competitionServiceStub = CompetitionServiceGrpc.newStub(newChannel("127.0.0.1", 900));
    public static ManagedChannel newChannel(String host, int port) {
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }
    public void request() {
        com.laozhu.proto.Competition.GetCompetitionReq getCompetitionReq = com.laozhu.proto.Competition.GetCompetitionReq.newBuilder().setName("competition1").build();
        competitionServiceStub.getCompetition(getCompetitionReq, new StreamObserver<Competition.GetCompetitionResp>() {
            @Override
            public void onNext(com.laozhu.proto.Competition.GetCompetitionResp value) {
                Log.i("demo", value.getName());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

}
