package com.revature.repository;

import com.revature.entity.Account;
import com.revature.exception.AccountSQLException;
import com.revature.utility.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteAccountDao implements AccountDao{

    @Override
    public Account createCheckingAccount(Account newAccount) {
        String sql = "insert into checking(user_id, gold, silver, copper) values (?, ?, ?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newAccount.getUserId());
            preparedStatement.setFloat(2, newAccount.getGold());
            preparedStatement.setFloat(3, newAccount.getSilver());
            preparedStatement.setFloat(4, newAccount.getCopper());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return newAccount;
            }
            throw new AccountSQLException("Checking account could not be created: please try again");
        } catch (SQLException exception){
            throw new AccountSQLException(exception.getMessage());
        }
    }

    @Override
    public Account createSavingAccount(Account newAccount) {
        String sql = "insert into saving(user_id, gold, silver, copper) values (?, ?, ?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newAccount.getUserId());
            preparedStatement.setFloat(2, newAccount.getGold());
            preparedStatement.setFloat(3, newAccount.getSilver());
            preparedStatement.setFloat(4, newAccount.getCopper());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return newAccount;
            }
            throw new AccountSQLException("Savings account could not be created: please try again");
        } catch (SQLException exception){
            throw new AccountSQLException(exception.getMessage());
        }
    }

    @Override
    public Account createInvestmentAccount(Account newAccount) {
        String sql = "insert into investment(user_id, gold, silver, copper) values (?, ?, ?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newAccount.getUserId());
            preparedStatement.setFloat(2, newAccount.getGold());
            preparedStatement.setFloat(3, newAccount.getSilver());
            preparedStatement.setFloat(4, newAccount.getCopper());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return newAccount;
            }
            throw new AccountSQLException("Investment account could not be created: please try again");
        } catch (SQLException exception){
            throw new AccountSQLException(exception.getMessage());
        }
    }
    
    @Override
    public List<Account> getAllAccounts() {
        String sql = "select * from checking";
        try(Connection connection = DatabaseConnector.createConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()){
                Account accountRecord = new Account();
                accountRecord.setUserId(resultSet.getInt("user_id"));
                accountRecord.setGold(resultSet.getFloat("gold"));
                accountRecord.setSilver(resultSet.getFloat("silver"));
                accountRecord.setCopper(resultSet.getFloat("copper"));
                accountRecord.setAccountID(resultSet.getInt("account_id"));
                accounts.add(accountRecord);
            }
            return accounts;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
        
    }
    
    @Override
    public Account getCheckingAccountByID(int id) {
        String sql = "select * from checking where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Account accountRecord = new Account();
            accountRecord.setUserId(resultSet.getInt("user_id"));
            accountRecord.setGold(resultSet.getFloat("gold"));
            accountRecord.setSilver(resultSet.getFloat("silver"));
            accountRecord.setCopper(resultSet.getFloat("copper"));
            accountRecord.setAccountID(resultSet.getInt("account_id"));
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }    

    @Override
    public Account getSavingAccountByID(int id) {
        String sql = "select * from saving where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Account accountRecord = new Account();
            accountRecord.setUserId(resultSet.getInt("user_id"));
            accountRecord.setGold(resultSet.getFloat("gold"));
            accountRecord.setSilver(resultSet.getFloat("silver"));
            accountRecord.setCopper(resultSet.getFloat("copper"));
            accountRecord.setAccountID(resultSet.getInt("account_id"));
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public Account getInvestmentAccountByID(int id) {
        String sql = "select * from investment where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Account accountRecord = new Account();
            accountRecord.setUserId(resultSet.getInt("user_id"));
            accountRecord.setGold(resultSet.getFloat("gold"));
            accountRecord.setSilver(resultSet.getFloat("silver"));
            accountRecord.setCopper(resultSet.getFloat("copper"));
            accountRecord.setAccountID(resultSet.getInt("account_id"));
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public Account depositChecking(Account acc, float g, float s, float c) {
        String sql = "update checking set gold = ?, silver = ?, copper = ? where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, acc.getGold() + g);
            preparedStatement.setFloat(2, acc.getSilver() + s);
            preparedStatement.setFloat(3, acc.getCopper() + c);
            preparedStatement.setInt(4, acc.getAccountID());
            preparedStatement.executeUpdate();
            Account accountRecord = getCheckingAccountByID(acc.getAccountID());
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public Account depositSaving(Account acc, float g, float s, float c) {
        String sql = "update saving set gold = ?, silver = ?, copper = ? where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, acc.getGold() + g);
            preparedStatement.setFloat(2, acc.getSilver() + s);
            preparedStatement.setFloat(3, acc.getCopper() + c);
            preparedStatement.setInt(4, acc.getAccountID());
            preparedStatement.executeUpdate();
            Account accountRecord = getSavingAccountByID(acc.getAccountID());
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public Account depositInvestment(Account acc, float g, float s, float c) {
        String sql = "update investment set gold = ?, silver = ?, copper = ? where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, acc.getGold() + g);
            preparedStatement.setFloat(2, acc.getSilver() + s);
            preparedStatement.setFloat(3, acc.getCopper() + c);
            preparedStatement.setInt(4, acc.getAccountID());
            preparedStatement.executeUpdate();
            Account accountRecord = getInvestmentAccountByID(acc.getAccountID());
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public Account withdrawChecking(Account acc, float g, float s, float c) {
        String sql = "update checking set gold = ?, silver = ?, copper = ? where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, acc.getGold() - g);
            preparedStatement.setFloat(2, acc.getSilver() - s);
            preparedStatement.setFloat(3, acc.getCopper() - c);
            preparedStatement.setInt(4, acc.getAccountID());
            preparedStatement.executeUpdate();
            Account accountRecord = getCheckingAccountByID(acc.getAccountID());    
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public Account withdrawSaving(Account acc, float g, float s, float c) {
        String sql = "update saving set gold = ?, silver = ?, copper = ? where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, acc.getGold() - g);
            preparedStatement.setFloat(2, acc.getSilver() - s);
            preparedStatement.setFloat(3, acc.getCopper() - c);
            preparedStatement.setInt(4, acc.getAccountID());
            preparedStatement.executeUpdate();
            Account accountRecord = getSavingAccountByID(acc.getAccountID());    
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public Account withdrawInvestment(Account acc, float g, float s, float c) {
        String sql = "update investment set gold = ?, silver = ?, copper = ? where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, acc.getGold() - g);
            preparedStatement.setFloat(2, acc.getSilver() - s);
            preparedStatement.setFloat(3, acc.getCopper() - c);
            preparedStatement.setInt(4, acc.getAccountID());
            preparedStatement.executeUpdate();
            Account accountRecord = getInvestmentAccountByID(acc.getAccountID());    
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }
    
    @Override
    public List<Account> getAllCheckingAccountsByUserID(int id) {
        String sql = "select * from checking where user_id = ?;";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()){
                Account accountRecord = new Account();
                accountRecord.setUserId(resultSet.getInt("user_id"));
                accountRecord.setGold(resultSet.getFloat("gold"));
                accountRecord.setSilver(resultSet.getFloat("silver"));
                accountRecord.setCopper(resultSet.getFloat("copper"));
                accountRecord.setAccountID(resultSet.getInt("account_id"));
                accounts.add(accountRecord);
            }
            return accounts;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public List<Account> getAllSavingAccountsByUserID(int id) {
        String sql = "select * from saving where user_id = ?;";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()){
                Account accountRecord = new Account();
                accountRecord.setUserId(resultSet.getInt("user_id"));
                accountRecord.setGold(resultSet.getFloat("gold"));
                accountRecord.setSilver(resultSet.getFloat("silver"));
                accountRecord.setCopper(resultSet.getFloat("copper"));
                accountRecord.setAccountID(resultSet.getInt("account_id"));
                accounts.add(accountRecord);
            }
            return accounts;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public List<Account> getAllInvestmentAccountsByUserID(int id) {
        String sql = "select * from investment where user_id = ?;";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()){
                Account accountRecord = new Account();
                accountRecord.setUserId(resultSet.getInt("user_id"));
                accountRecord.setGold(resultSet.getFloat("gold"));
                accountRecord.setSilver(resultSet.getFloat("silver"));
                accountRecord.setCopper(resultSet.getFloat("copper"));
                accountRecord.setAccountID(resultSet.getInt("account_id"));
                accounts.add(accountRecord);
            }
            return accounts;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }    
   
    @Override
    public void deleteCheckingAccount(Account acc) {
        String sql = "delete from checking where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, acc.getAccountID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public void deleteSavingAccount(Account acc) {
        String sql = "delete from saving where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, acc.getAccountID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public void deleteInvestmentAccount(Account acc) {
        String sql = "delete from investment where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, acc.getAccountID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }
 
}
