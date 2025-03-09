import { MongoClient } from 'mongodb';

export const connect = async (mongoUrl: string = 'mongodb://localhost:27017') => {
  try {
    const client = await MongoClient.connect(mongoUrl);
    console.log('Connected to MongoDB');
    return client;
  } catch (error) {
    console.error('Failed to connect to MongoDB:', error);
    throw error;
  }
};