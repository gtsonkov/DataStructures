package core;

import interfaces.Entity;
import interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;

public class Data implements Repository {
    private Queue<Entity> data;
    private Entity root;

    public Data(){
        this.data = new PriorityQueue<>();
        this.root = null;
    }

    public Data(Data other) {
        this.root = other.root;
        this.data = new PriorityQueue<>(other.data);
    }

    @Override
    public void add(Entity entity) {
        if(this.root == null){
            this.root = entity;
        } else {
            this.getById(entity.getParentId()).addChild(entity);
        }
        this.data.offer(entity);
    }

    @Override
    public Entity getById(int id) {
        if (this.root == null){
            return null;
        }

        Deque<Entity> tree = new ArrayDeque<>();
        tree.offer(root);
        while (!tree.isEmpty()){
            Entity current = tree.poll();
            if (current.getId() == id){
                return current;
            }
            for (Entity child: current.getChildren()) {
                tree.offer(child);
            }
        }
        return null;
    }

    @Override
    public List<Entity> getByParentId(int id) {
        Entity parent = getById(id);
        if (parent == null){
            return null;
        }
        return parent.getChildren();
    }

    @Override
    public List<Entity> getAll() {
        return new ArrayList<>(this.data);
    }

    @Override
    public Repository copy() {
        return new Data(this);
    }

    @Override
    public List<Entity> getAllByType(String type) {
        if (!type.equals("User") && !type.equals("Invoice") && !type.equals("StoreClient")){
            throw new IllegalStateException("IlLegal type: " + type);
        }
        return this.data
                .stream()
                .filter(e->e.getClass().getSimpleName().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public Entity peekMostRecent() {
        if (this.data.size() == 0){
            throw new IllegalStateException("Operation on empty data");
        }
        return this.data.peek();
    }

    @Override
    public Entity pollMostRecent() {
        if (this.data.size() == 0){
            throw new IllegalStateException("Operation on empty data");
        }
        Entity result = this.data.poll();
        this.getById(result.getParentId()).getChildren().remove(result);

        return result;
    }

    @Override
    public int size() {
        return this.data.size();
    }
}