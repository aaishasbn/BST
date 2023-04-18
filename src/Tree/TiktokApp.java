package Tree;
import java.util.ArrayList;

/**
 * This class represents a Tiktok system which manages Tiktok accounts and their posts.
 */
public class TiktokApp {
/**
     * The root node of the binary search tree storing the Tiktok accounts.
     */
    private BSTNode root;
 /**
     * This private class represents a node in the binary search tree which stores Tiktok accounts.
     */
    private class BSTNode {
        public String account;
        public String profileDescription;
        public ArrayList<Post> posts;
        public BSTNode left;
        public BSTNode right;
 /**
         * This constructor creates a new BSTNode with the given account and profile description.
         * @param account the account name
         * @param profileDescription the profile description
         */
        public BSTNode(String account, String profileDescription) {
            this.account = account;
            this.profileDescription = profileDescription;
            this.posts = new ArrayList<Post>();
            this.left = null;
            this.right = null;
        }
    }
    /**
     * This private class represents a Tiktok post.
     */

    private class Post {
        public String title;
        public String videoClipFile;
        public int numLikes;
/**
         * This constructor creates a new Post with the given title, video clip file, and number of likes.
         * @param title the post title
         * @param videoClipFile the video clip file name
         * @param numLikes the number of likes for the post
         */
        public Post(String title, String videoClipFile, int numLikes) {
            this.title = title;
            this.videoClipFile = videoClipFile;
            this.numLikes = numLikes;
        }
    }
 /**
     * This method adds a new Tiktok account with the given account name and profile description.
     * @param account the account name
     * @param profileDescription the profile description
     */
    public BSTNode getAccount(String account) {
        return getAccountHelper(root, account);
    }
    /** private helper method recursively adds a new Tiktok account with the given account name and profile description.
@param node the root of the subtree to add the account to
@param account the account name
@param profileDescription the profile description
@return the root of the updated subtree
*/
    
    private BSTNode getAccountHelper(BSTNode node, String account) {
        if (node == null) {
            return null;
        }
        if (account.equals(node.account)) {
            return node;
        } else if (account.compareTo(node.account) < 0) {
            return getAccountHelper(node.left, account);
        } else {
            return getAccountHelper(node.right, account);
        }
    }
    
    public void addAccount(String account, String profileDescription) {
        root = addAccountHelper(root, account, profileDescription);
    }
  /**
     * This private helper method recursively adds a new Tiktok account with the given account name and profile description.
     * @param node the root of the subtree to add the account to
     * @param account the account name
     * @param profileDescription the profile description
     * @return the root of the updated subtree
     */
    private BSTNode addAccountHelper(BSTNode node, String account, String profileDescription) {
        if (node == null) {
            return new BSTNode(account, profileDescription);
        }
        if (account.compareTo(node.account) < 0) {
            node.left = addAccountHelper(node.left, account, profileDescription);
        } else if (account.compareTo(node.account) > 0) {
            node.right = addAccountHelper(node.right, account, profileDescription);
        }
        return node;
    }
    /**
     * This method deletes a Tiktok account with the given account name.
     * @param account the account name
     */

    public void deleteAccount(String account) {
        root = deleteAccountHelper(root, account);
    }
/**
     * This private helper method recursively deletes a Tiktok account with the given account name.
     * @param node the root of the subtree to delete the account from
     * @param account the account name
     * @return the root of the updated subtree
     */
    private BSTNode deleteAccountHelper(BSTNode node, String account) {
        if (node == null) {
            return null;
        }
        if (account.compareTo(node.account) < 0) {
            node.left = deleteAccountHelper(node.left, account);
        } else if (account.compareTo(node.account) > 0) {
            node.right = deleteAccountHelper(node.right, account);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                BSTNode temp = findMin(node.right);
                node.account = temp.account;
                node.profileDescription = temp.profileDescription;
                node.posts = temp.posts;
                node.right = deleteAccountHelper(node.right, node.account);
            }
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public String findProfileDescription(String account) {
        return findProfileDescriptionHelper(root, account);
    }

    private String findProfileDescriptionHelper(BSTNode node, String account) {
        if (node == null) {
            return null;
        }
        if (account.equals(node.account)) {
            return node.profileDescription;
        } else if (account.compareTo(node.account) < 0) {
            return findProfileDescriptionHelper(node.left, account);
        } else {
            return findProfileDescriptionHelper(node.right, account);
        }
    }

    public ArrayList<String> listAccounts() {
        ArrayList<String> result = new ArrayList<String>();
        listAccountsHelper(root, result);
        return result;
    }

    private void listAccountsHelper(BSTNode node, ArrayList<String> result) {
        if (node != null) {
            listAccountsHelper(node.left, result);
            result.add(node.account);
            listAccountsHelper(node.right, result);
        }
    }

    public ArrayList<Post> getPosts(String account) {
        return getPostsHelper(root, account);
    }

    private ArrayList<Post> getPostsHelper(BSTNode node, String account) {
        if (node == null) {
            return null;
        }
        if (account.equals(node.account)) {
            return node.posts;
        } else if (account.compareTo(node.account) < 0) {
            return getPostsHelper(node.left, account);
        } else {
            return getPostsHelper(node.right, account);
        }
    }
/**
 *  adds a new Tiktok post to the account with the given account name.
 * @param account the account name
 * @param title the post title
 * @param videoClipFile the video clip file name
 * @param numLikes the number of likes for the post
 */
    public void addPost(String account, String title, String videoClipFile, int numLikes) {
        addPostHelper(root, account, title, videoClipFile, numLikes);
    }

    private void addPostHelper(BSTNode node, String account, String title, String videoClipFile, int numLikes) {
        if (node == null) {
            return;
        }
        if (account.equals(node.account)) {
            node.posts.add(0, new Post(title, videoClipFile, numLikes));
        } else if (account.compareTo(node.account) < 0) {
            addPostHelper(node.left, account, title, videoClipFile, numLikes);
        } else {
            addPostHelper(node.right, account, title, videoClipFile, numLikes);
        }
    }
    /**
 * This method returns the total number of likes for all the posts of the account with the given account name.
 * @param account the account name
 * @return the total number of likes for all the posts of the account with the given account name
 */
public int getNumLikes(String account) {
    BSTNode node = getAccount(account);
    int totalLikes = 0;
    if (node != null) {
        for (int i = 0; i < node.posts.size(); i++) {
            totalLikes += node.posts.get(i).numLikes;
        }
    }
    return totalLikes;
}
/**
 * This method returns the account name with the highest number of total likes across all of its posts.
 * If there are multiple accounts with the same highest number of total likes, the method returns the first account found.
 * @return the account name with the highest number of total likes across all of its posts
 */
public String getMostPopularAccount() {
    String mostPopularAccount = "";
    int maxLikes = 0;
    ArrayList<String> accounts = listAccounts();
    for (int i = 0; i < accounts.size(); i++) {
        int numLikes = getNumLikes(accounts.get(i));
        if (numLikes > maxLikes) {
            maxLikes = numLikes;
            mostPopularAccount = accounts.get(i);
        }
    }
    return mostPopularAccount;
}

    public static void main(String[] args) {
        TiktokApp system = new TiktokApp();
        system.addAccount("Mary", "Had a little lamb!");
        system.addAccount("Bob", "I am a minion!");
        system.addPost("Mary", "Lamb video", "video1.mp4", 100);
        system.addPost("Mary", "Lamb jumping video", "video2.mp4", 200);
        system.addPost("Bob", "Flossing video", "video3.mp4", 150);
        ArrayList<String> accounts = system.listAccounts();
        System.out.println("List of accounts:");
        for (String account : accounts) {
            System.out.println(account);
        }
        String profileDescription = system.findProfileDescription("Mary");
        System.out.println("\nProfile description for Mary:");
        System.out.println(profileDescription);
        ArrayList<Post> posts = system.getPosts("Mary");
        System.out.println("\nList of posts for Mary:");
        for (Post post : posts) {
            System.out.println(post.title + " " + post.videoClipFile + " " + post.numLikes);
        }
        system.deleteAccount("Mary");
        accounts = system.listAccounts();
        System.out.println("\nList of accounts after deleting Mary:");
        for (String account : accounts) {
            System.out.println(account);
        }
    }
}
