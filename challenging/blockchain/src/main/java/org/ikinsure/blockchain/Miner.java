package org.ikinsure.blockchain;

import org.ikinsure.blockchain.block.BlockInfo;
import org.ikinsure.blockchain.block.BlockManager;

import java.util.Random;

public class Miner {

    private final User user;
    private final BlockManager manager;
    private final Random random;

    public Miner(User user, BlockManager manager) {
        this.user = user;
        this.manager = manager;
        this.random = new Random();
    }

    public String mine() {
        final int size = manager.size();
        String magic;
        String hash;
        do {
            magic = String.valueOf(random.nextInt());
            BlockInfo info = manager.getInfo();
            if (size != manager.size() || info == null) {
                return null;
            }
            hash = Util.sha256(info, magic);
        } while (!Util.validate(hash, manager.getZeros()));
        return magic;
    }

    public User getUser() {
        return user;
    }
}
