package ro.sdi.core.service;

import java.util.List;

import ro.sdi.core.model.Pod;

public interface PodService
{
    Pod add(String name, Integer cost);

    List<Pod> getPods();

    List<Pod> getAvailablePods();

    Pod delete(Long podId);

}
