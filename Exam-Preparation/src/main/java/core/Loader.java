package core;

import interfaces.Buffer;
import interfaces.Entity;
import model.BaseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Loader implements Buffer {

    private List<Entity> data;

    public Loader (){
        this.data = new ArrayList<>();
    }
    @Override
    public void add(Entity entity) {
        this.data.add(entity);
    }

    @Override
    public Entity extract(int id) {
        Entity toReturn = null;
        for (Entity entity: data) {
            if (entity.getId() == id){
                toReturn = entity;
                data.remove(entity);
                break;
            }
        }
        return toReturn;
    }

    @Override
    public Entity find(Entity entity) {
        Entity toReturn = null;
        for (Entity currEntity: data) {
            if (currEntity.compareTo(entity)==0){
                toReturn = currEntity;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public boolean contains(Entity entity) {
        return this.data.contains(entity);
    }

    @Override
    public int entitiesCount() {
        return this.data.size();
    }

    @Override
    public void replace(Entity oldEntity, Entity newEntity) {
        if (!this.data.contains(oldEntity)){
            throw new IllegalStateException("Entity not found");
        }
        int index = this.data.indexOf(oldEntity);
        this.data.set(index,newEntity);
    }

    @Override
    public void swap(Entity first, Entity second) {
        int firsIndex = this.data.indexOf(first);
        int secondIndex = this.data.indexOf(second);
        if (firsIndex < 0 || secondIndex < 0 ){
            throw new IllegalStateException("Entities not found");
        }
        this.data.set(firsIndex,second);
        this.data.set(secondIndex,first);
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public Entity[] toArray() {
        Entity[] array = new Entity[data.size()];
        this.data.toArray(array);
        return array;
    }

    @Override
    public List<Entity> retainAllFromTo(BaseEntity.Status lowerBound, BaseEntity.Status upperBound) {
        return this.data
                .stream()
                .filter(e -> e.getStatus().ordinal() >= lowerBound.ordinal()
                        && e.getStatus().ordinal() <= upperBound.ordinal())
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> getAll() {
        return new ArrayList<>(this.data);
    }

    @Override
    public void updateAll(BaseEntity.Status oldStatus, BaseEntity.Status newStatus) {
        for (Entity element : this.data) {
            if (element.getStatus().equals(oldStatus)){
                element.setStatus(newStatus);
            }
        }
    }

    @Override
    public void removeSold() {
        this.data = this.data.stream()
                .filter(e -> !e.getStatus().equals(BaseEntity.Status.SOLD))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Entity> iterator() {
        return this.data.iterator();
    }
}